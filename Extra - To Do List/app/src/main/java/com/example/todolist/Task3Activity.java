package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Task3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        TextView task3Text = findViewById(R.id.task3Text);
        task3Text.setText("Finish reading up Harry Potter and the Philosopher's Stone.");

    }
}