package com.example.movingbanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView banner = findViewById(R.id.banner);
        TextView locationText = findViewById(R.id.locationText);
        Button startButton = findViewById(R.id.startButton);

        int coords[] = new int[2];  //For getting on screen coordinates

        Thread translateThread = new Thread(new Runnable() {    //For the translation
            @Override
            public void run() {
                int i = 0;

                while(i < 50){
                    try{
                        Thread.sleep(500);
                        i++;

                        banner.animate().translationXBy(10f);
                        banner.getLocationOnScreen(coords);
                        locationText.setText("Location: " + coords[0] + " " + coords[1]);

                    } catch(Exception e){
                        //Do nothing
                    }
                }
            }
        });

        Thread colorThread = new Thread(new Runnable() {    //For the coloring
            @Override
            public void run() {
                int i = 0;

                while(i < 50){
                    try{
                        Thread.sleep(500);
                        i++;

                        if(i % 3 == 0){
                            banner.setBackgroundColor(Color.RED);
                        } else if(i % 3 == 1){
                            banner.setBackgroundColor(Color.BLUE);
                        } else{
                            banner.setBackgroundColor(Color.GREEN);
                        }

                    } catch (Exception e){
                        //Do nothing
                    }
                }
            }
        });

        //To begin the threads with button click
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateThread.start();
                colorThread.start();
            }
        });
    }
}