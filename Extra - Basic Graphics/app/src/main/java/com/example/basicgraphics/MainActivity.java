package com.example.basicgraphics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView rocketImage = findViewById(R.id.rocketImage);
        ImageView spannerImage = findViewById(R.id.spannerImage);
        Button animateButton = findViewById(R.id.animateButton);

        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                Animation zoomAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);

                rocketImage.startAnimation(zoomAnimation);
                spannerImage.startAnimation(rotateAnimation);
            }
        });
    }
}