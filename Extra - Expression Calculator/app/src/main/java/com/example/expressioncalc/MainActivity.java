package com.example.expressioncalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultText = findViewById(R.id.resultText);
        EditText exprText = findViewById(R.id.exprText);
        Button evalButton = findViewById(R.id.evalButton);

        evalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expr = exprText.getText().toString().trim();

                if(expr != "" && expr != null){
                    try{
                        Double result = new DoubleEvaluator().evaluate(expr);
                        resultText.setText("Result: " + result);
                    } catch(Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else{
                    Toast.makeText(getApplicationContext(), "Enter an expression!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}