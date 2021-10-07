package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView alarmText = findViewById(R.id.alarmText);
        final TimePicker timePicker = findViewById(R.id.timePicker);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        final Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ToggleButton) v).isChecked()){
                    //Turn the alarm on

                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    calendar.set(Calendar.SECOND, 0);

                    //If time in the past was chosen, move alarm to the next day
                    if(calendar.before(Calendar.getInstance())) {
                        calendar.add(Calendar.DATE, 1);
                    }

                    Log.i("Time ", calendar.getTime().toString());
                    alarmText.setText("Alarm Set For: " + DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()) + " \uD83D\uDD14");


                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pendingIntent);
                    Toast.makeText(MainActivity.this, "ALARM SET!", Toast.LENGTH_SHORT).show();

                } else{
                    //Turn the alarm off

                    alarmManager.cancel(pendingIntent);     //Cancel the pending alarm intent

                    if(AlarmReceiver.ringtone != null && AlarmReceiver.ringtone.isPlaying()){
                        //If the ringtone is playing i.e. the alarm is ringing and the ringtone object has been set
                        AlarmReceiver.ringtone.stop();  //Stop the ringtone started from the alarm receiver
                    }

                    Toast.makeText(MainActivity.this, "ALARM OFF!", Toast.LENGTH_SHORT).show();
                    alarmText.setText("Alarm Not Set \uD83D\uDEAB");
                }
            }
        });
    }
}