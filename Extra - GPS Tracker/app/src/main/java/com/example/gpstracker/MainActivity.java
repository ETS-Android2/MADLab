package com.example.gpstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get permissions from the user for the required actions
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.INTERNET,
                            Manifest.permission.SEND_SMS}, 1);

        TextView latitudeText = findViewById(R.id.latitudeText);
        TextView longitudeText = findViewById(R.id.longitudeText);
        TextView readLatitudeText = findViewById(R.id.readLatitudeText);
        TextView readLongitudeText = findViewById(R.id.readLongitudeText);
        Button writeButton = findViewById(R.id.writeButton);
        Button readButton = findViewById(R.id.readButton);


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener(){

            @Override
            public void onLocationChanged(@NonNull Location location) {
                //If location has changed, update it in the UI
                latitudeText.setText(Double.toString(location.getLatitude()).substring(0, 7));
                longitudeText.setText(Double.toString(location.getLongitude()).substring(0, 7));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };

        //Request location updates every 5 seconds
        //Necessary for location listener callbacks to work
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileContents =   "Latitude: " + latitudeText.getText().toString() +
                                        "\nLongitude: " + longitudeText.getText().toString();

                File file = new File(getExternalFilesDir("GPS"), "Location.txt");

                try{
                    //Write to File
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(fileContents.getBytes());
                    fileOutputStream.close();

                    //Send SMS
                    PendingIntent sentIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("Sent"), 0);
                    PendingIntent deliveredIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("Delivered"), 0);

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("5554", null, fileContents, sentIntent, deliveredIntent);

                    Toast successToast = Toast.makeText(MainActivity.this, "Location Noted!", Toast.LENGTH_SHORT);
                    successToast.show();
                } catch(Exception e){
                    Toast errorToast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir("GPS"), "Location.txt");

                try{
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine().substring(9);   //Remove "Latitude:"
                    readLatitudeText.setText(line);
                    line = bufferedReader.readLine().substring(10);     //Remove "Longitude:"
                    readLongitudeText.setText(line);
                    bufferedReader.close();
                    fileReader.close();
                } catch (Exception e){
                    Toast errorToast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    errorToast.show();
                }



            }
        });

    }
}