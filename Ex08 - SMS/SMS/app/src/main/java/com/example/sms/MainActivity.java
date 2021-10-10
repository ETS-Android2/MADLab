package com.example.sms;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.SEND_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    TextView historyText;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //To set message text upon reception of message from BroadcastReceiver
            historyText.setText(
                    historyText.getText() + intent.getExtras().getString("message"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request user permissions for SMS
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{READ_SMS, SEND_SMS, READ_PHONE_STATE}, 1);

        historyText = findViewById(R.id.historyText);

        final EditText phoneText = findViewById(R.id.phoneText);
        final EditText messageText = findViewById(R.id.messageText);
        final ImageButton sendButton = findViewById(R.id.sendButton);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneText.getText().toString();
                String message = messageText.getText().toString();

                sendMessage(phone, message);
            }
        });
    }

    void sendMessage(String phone, String message){
        //To send a message using SmsManager
        final String SENT = "Message Sent";
        final String DELIVERED = "Message Delivered";

        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, sentIntent, deliveredIntent);
    }

    @Override
    protected void onPause(){
        unregisterReceiver(intentReceiver);
        super.onPause();
    }

    @Override
    protected void onResume(){
        registerReceiver(intentReceiver, intentFilter);
        super.onResume();
    }
}