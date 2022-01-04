package com.example.labpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsactivity);

        final TextView textView = findViewById(R.id.textView);
        final TextView latitudeText = findViewById(R.id.latitudeText);
        final TextView longitudeText = findViewById(R.id.longitudeText);
        final TextView locationText = findViewById(R.id.locationText);
        final TextView sdLatitudeText = findViewById(R.id.sdLatitudeText);
        final TextView sdLongitudeText = findViewById(R.id.sdLongitudeText);
        final TextView sdLocationText = findViewById(R.id.sdLocationText);
        final Button writeButton = findViewById(R.id.writeButton);
        final Button readButton = findViewById(R.id.readButton);
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Intent gpsIntent = getIntent();
        String value = gpsIntent.getStringExtra("Test");

        textView.setText(textView.getText() + " " + value); //Intent transfer data

        ActivityCompat.requestPermissions(GPSActivity.this, new String[]
                {Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    locationText.setText(addressList.get(0).getAddressLine(0));
                } catch (IOException e) {
                    //Ignore
                }

                latitudeText.setText(Double.toString(location.getLatitude()).substring(0, 7));
                longitudeText.setText(Double.toString(location.getLongitude()).substring(0, 7));
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = "Latitude: " + latitudeText.getText().toString() +
                                    "\nLongitude: " + longitudeText.getText().toString() +
                                    "\nLocation: " + locationText.getText().toString();

                File file = new File(getExternalFilesDir("LabPractice"), "Location.txt");

                try{
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(contents.getBytes());
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(), "Written Successfully!", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = "";
                File file = new File(getExternalFilesDir("LabPractice"), "Location.txt");

                try{
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String line = bufferedReader.readLine().substring(9); //Remove "Latitude:"
                    sdLatitudeText.setText(line);

                    line = bufferedReader.readLine().substring(10); //Remove "Longitude:"
                    sdLongitudeText.setText(line);

                    line = bufferedReader.readLine().substring(9); //Remove "Location:"
                    sdLocationText.setText(line);

                    bufferedReader.close();
                    fileReader.close();

                    Toast.makeText(getApplicationContext(), "Read Successfully!", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}