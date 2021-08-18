package com.example.graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button shapesButton = findViewById(R.id.shapesButton);
        final Button animateButton = findViewById(R.id.animateButton);
        final Button transformButton = findViewById(R.id.transformButton);

        shapesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shapesIntent = new Intent(MainActivity.this, ShapesActivity.class);
                startActivity(shapesIntent);
            }
        });
        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animateIntent = new Intent(MainActivity.this, AnimateActivity.class);
                startActivity(animateIntent);
            }
        });
        transformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transformIntent = new Intent(MainActivity.this, TransformActivity.class);
                startActivity(transformIntent);
            }
        });
    }
}