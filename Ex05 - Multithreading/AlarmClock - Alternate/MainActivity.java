package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    public String AlarmTime(TimePicker timePicker){
        //To convert the current TimePicker's time value to a String

        Integer alarmHour = timePicker.getHour();
        Integer alarmMinute = timePicker.getMinute();
        String alarmTime, suffix = "AM", minute, hour;

        if(alarmHour >= 12){
            suffix = "PM";

            if(alarmHour != 12){
                alarmHour -= 12;
            }
        }

        if(alarmMinute < 10){
            minute = "0" + Integer.toString(alarmMinute);
        } else{
            minute = Integer.toString(alarmMinute);
        }

        if(alarmHour == 0){
            hour = "12";
        } else{
            hour = Integer.toString(alarmHour);
        }

        alarmTime = hour + ":" + minute + " " + suffix;

        return alarmTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextClock textClock = findViewById(R.id.textClock);
        final Button setAlarmButton = findViewById(R.id.setAlarmButton);
        final Button cancelAlarmButton = findViewById(R.id.cancelAlarmButton);
        final TimePicker timePicker = findViewById(R.id.timePicker);
        final TextView statusText = findViewById(R.id.statusText);

        final AtomicInteger stop = new AtomicInteger(0);    //Thread-safe integer that can be updated atomically
        final Timer timer = new Timer();
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(v.VISIBLE);    //Make the timePicker visible to set alarm time
                stop.getAndSet(0);  //Set the semaphore to 0, enables the alarm
                cancelAlarmButton.setEnabled(true); //Enable cancel alarm button

                //Execute the TimerTask instantly (delay = 0) and check every 1s (period = 1000)
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        //Log.i("TextClock Value: ", textClock.getText().toString());
                        //Log.i("TimePicker Value: ", AlarmTime(timePicker));

                        if(textClock.getText().toString().equals(AlarmTime(timePicker)) && stop.get() == 0){
                            ringtone.play();
                            //Log.i("Playing Alarm Tone", "Working!");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    statusText.setText("Alarm is ringing! \uD83D\uDD14");
                                }
                            });
                        } else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    statusText.setText("Alarm is not ringing. \uD83D\uDEAB");
                                }
                            });
                            ringtone.stop();
                        }
                    }
                }, 0, 1000);
            }
        });

        cancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.getAndSet(1);  //Set the semaphore to 1, disables the alarm
                cancelAlarmButton.setEnabled(false); //Disable cancel alarm button
                timePicker.setVisibility(v.INVISIBLE);
            }
        });
    }
}
