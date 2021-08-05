package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Double input1 = 0.0, input2 = 0.0, result = 0.0;
    Boolean addFlag = false, subFlag = false, mulFlag = false, divFlag = false, powFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText displayArea = findViewById(R.id.displayArea);
        final Button num0 = findViewById(R.id.num0);
        final Button num1 = findViewById(R.id.num1);
        final Button num2 = findViewById(R.id.num2);
        final Button num3 = findViewById(R.id.num3);
        final Button num4 = findViewById(R.id.num4);
        final Button num5 = findViewById(R.id.num5);
        final Button num6 = findViewById(R.id.num6);
        final Button num7 = findViewById(R.id.num7);
        final Button num8 = findViewById(R.id.num8);
        final Button num9 = findViewById(R.id.num9);
        final Button decimal = findViewById(R.id.decimal);
        final Button clear = findViewById(R.id.clear);
        final Button power = findViewById(R.id.power);
        final Button divide = findViewById(R.id.divide);
        final Button multiply = findViewById(R.id.multiply);
        final Button subtract = findViewById(R.id.subtract);
        final Button add = findViewById(R.id.add);
        final Button equal = findViewById(R.id.equal);

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "0");
            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "1");
            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "2");
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "3");
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "4");
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "5");
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "6");
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "7");
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "8");
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText(displayArea.getText() + "9");
            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!displayArea.getText().toString().contains(".")) {
                    displayArea.setText(displayArea.getText() + ".");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayArea.setText("");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayArea.getText().length() != 0){
                    input1 = Double.parseDouble(displayArea.getText().toString());
                    displayArea.setText("");
                }

                //if operator was pressed without any new input
                addFlag = true;
                subFlag = mulFlag = divFlag = powFlag = false;
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayArea.getText().length() != 0){
                    input1 = Double.parseDouble(displayArea.getText().toString());
                    displayArea.setText("");
                }

                //if operator was pressed without any new input
                subFlag = true;
                addFlag = mulFlag = divFlag = powFlag = false;
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayArea.getText().length() != 0){
                    input1 = Double.parseDouble(displayArea.getText().toString());
                    displayArea.setText("");
                }

                //if operator was pressed without any new input
                mulFlag = true;
                addFlag = subFlag = divFlag = powFlag = false;
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayArea.getText().length() != 0){
                    input1 = Double.parseDouble(displayArea.getText().toString());
                    displayArea.setText("");
                }

                //if operator was pressed without any new input
                divFlag = true;
                addFlag = subFlag = mulFlag = powFlag = false;
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayArea.getText().length() != 0){
                    input1 = Double.parseDouble(displayArea.getText().toString());
                    displayArea.setText("");
                }

                //if operator was pressed without any new input
                powFlag = true;
                addFlag = subFlag = mulFlag = divFlag = false;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((addFlag || subFlag || mulFlag || divFlag || powFlag) && displayArea.getText().length() != 0){
                    input2 = Double.parseDouble(displayArea.getText().toString());
                }

                if(addFlag){
                    result = input1 + input2;
                    displayArea.setText(result + "");
                    input1 = input2 = result = 0.0;
                }

                if(subFlag){
                    result = input1 - input2;
                    displayArea.setText(result + "");
                    input1 = input2 = result = 0.0;
                }

                if(mulFlag){
                    result = input1 * input2;
                    displayArea.setText(result + "");
                    input1 = input2 = result = 0.0;
                }

                if(divFlag){
                    result = input1 / input2;
                    displayArea.setText(result + "");
                    input1 = input2 = result = 0.0;
                }

                if(powFlag){
                    result = Math.pow(input1, input2);
                    displayArea.setText(result + "");
                    input1 = input2 = result = 0.0;
                }

                //set all operations to false
                addFlag = subFlag = mulFlag = divFlag = powFlag = false;
            }
        });
    }
}