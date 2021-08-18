package com.example.graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);

        final ImageView syncImage = findViewById(R.id.syncImage);
        final ImageView starImage = findViewById(R.id.starImage);
        final ImageView dollarImage = findViewById(R.id.dollarImage);

        Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        syncImage.startAnimation(rotateAnimation);

        Animation zoomAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        starImage.startAnimation(zoomAnimation);

        Animation fadeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        dollarImage.startAnimation(fadeAnimation);
    }
}