package com.example.alarmclock;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.widget.Toast;



public class AlarmReceiver extends BroadcastReceiver {
    public static Ringtone ringtone = null;    //Important - Required to stop the ringtone from the main activity

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Time!", Toast.LENGTH_LONG).show();

        ringtone = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        ringtone.play();
    }
}
