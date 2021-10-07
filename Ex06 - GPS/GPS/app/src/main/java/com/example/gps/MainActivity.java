package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText locationText = findViewById(R.id.locationText);
        TextView latitudeText = findViewById(R.id.latitudeText);
        TextView longitudeText = findViewById(R.id.longitudeText);
        TextView addressText = findViewById(R.id.addressText);
        Button getButton = findViewById(R.id.getButton);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            //If permissions are not granted, display message to user and exit
            Toast permissionToast = Toast.makeText(MainActivity.this, "Please enable location permission for the application to work!", Toast.LENGTH_SHORT);
            permissionToast.show();
            return;
        }

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String location = locationText.getText().toString();
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    List<Address> addressList = null;
                    addressList = geocoder.getFromLocationName(location, 1);

                    if(addressList != null && addressList.size() > 0){
                        Address address = addressList.get(0);
                        latitudeText.setText(Double.toString(address.getLatitude()).substring(0, 7));
                        longitudeText.setText(Double.toString(address.getLongitude()).substring(0, 7));
                        addressText.setText(address.getAddressLine(0));
                    }

                    else{
                        Toast errorToast = Toast.makeText(MainActivity.this, "Location Not Found!", Toast.LENGTH_SHORT);
                        errorToast.show();
                        latitudeText.setText("");
                        longitudeText.setText("");
                        addressText.setText("");
                    }

                } catch (Exception e) {
                    Toast errorToast = Toast.makeText(MainActivity.this, "Error: "  + e.getMessage(), Toast.LENGTH_SHORT);
                    errorToast.show();
                    latitudeText.setText("");
                    longitudeText.setText("");
                    addressText.setText("");
                }
            }
        });
    }
}