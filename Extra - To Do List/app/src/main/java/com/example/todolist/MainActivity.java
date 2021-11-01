package com.example.todolist;

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

        Button task1View = findViewById(R.id.task1Button);
        Button task2View = findViewById(R.id.task2Button);
        Button task3View = findViewById(R.id.task3Button);

        task1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent task1Intent = new Intent(getApplicationContext(), Task1Activity.class);
                startActivity(task1Intent);
            }
        });

        task2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent task2Intent = new Intent(getApplicationContext(), Task2Activity.class);
                startActivity(task2Intent);
            }
        });

        task3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent task3Intent = new Intent(getApplicationContext(), Task3Activity.class);
                startActivity(task3Intent);
            }
        });

    }
}