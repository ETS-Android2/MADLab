package com.example.sms;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Date;

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages;
        String msgThread = "";

        if(bundle != null){
            //Received messages as raw Protocol Data Units (PDUs)
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus != null ? pdus.length : 0];

            //Get all SMS Messages and store it in the thread
            for(int i = 0; i < messages.length; i++){
                messages[i] = SmsMessage.createFromPdu((byte[]) (pdus != null ? pdus[i] : null));
                msgThread += messages[i].getOriginatingAddress();
                msgThread += ": ";
                msgThread += messages[i].getMessageBody();
                msgThread += "\n";
            }

            Toast.makeText(context, msgThread, Toast.LENGTH_SHORT).show();

            //Notification for received message
            //Prefer NotificationCompat over Notification
            NotificationCompat.Builder msgBuilder =
                    new NotificationCompat.Builder(context, "notify_channel")
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("You have received a message.")
                            .setContentText(msgThread)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            //Set the notification through a MainActivity intent
            Intent notificationIntent = new Intent(context, MainActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
            msgBuilder.setContentIntent(contentIntent);

            //Add a notification through the notify_channel channel
            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel("notify_channel",
                        "Notification Channel", NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                msgBuilder.setChannelId("notify_channel");
            }

            //Unique ID, NotificationCompat object (msgBuilder)
            manager.notify((int) (new Date().getTime()/1000 % Integer.MAX_VALUE), msgBuilder.build());
            Log.i("SMS", "SENT NOTIFICATION!");

            //Broadcast the message reception to the MainActivity to trigger onReceive() method
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            broadcastIntent.putExtra("message", msgThread);
            //put the msgThread in key "message" to correspond with MainActivity's onReceive()
            context.sendBroadcast(broadcastIntent);
        }
    }
}
