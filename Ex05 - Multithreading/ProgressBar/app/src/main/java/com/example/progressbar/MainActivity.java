package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText durationText = findViewById(R.id.durationText);
        final Button startButton = findViewById(R.id.startButton);
        final Button sleepButton = findViewById(R.id.sleepButton);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final Handler handler = new Handler();

        sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("\uD83D\uDD15 Sleep in Progress...");
                progressDialog.setMessage("Sleeping \uD83D\uDCA4");
                progressDialog.show();

                int duration = Integer.parseInt(durationText.getText().toString());

                //Dismiss the progressDialog after waiting for the sleep duration
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, duration * 1000);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                startButton.setText("IN PROGRESS...");

                //Runs in a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i <= 100; i+=5){
                            if(i == 100){
                                //Re-enable the button once the loop is over
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        startButton.setEnabled(true);
                                        startButton.setText("START");
                                    }
                                });
                            }

                            progressBar.setProgress(i);

                            //Wait for 100ms before the next iteration
                            try{
                                Thread.sleep(100);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}