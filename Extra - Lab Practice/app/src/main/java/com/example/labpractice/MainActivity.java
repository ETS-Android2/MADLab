package com.example.labpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button gpsButton = findViewById(R.id.gpsButton);
        final Button smsButton = findViewById(R.id.smsButton);
        final Button webButton = findViewById(R.id.webButton);
        final EditText dateText = findViewById(R.id.dateText);

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gpsIntent = new Intent(getApplicationContext(), GPSActivity.class);
                gpsIntent.putExtra("Test", "Hello and Welcome!");
                startActivity(gpsIntent);
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(getApplicationContext(), SMSActivity.class);
                startActivity(smsIntent);
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(getApplicationContext(), WebActivity.class);
                startActivity(webIntent);
            }
        });

        dateText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar c = Calendar.getInstance();

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dateText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                    datePickerDialog.show();
                }
            }
        });

    }
}