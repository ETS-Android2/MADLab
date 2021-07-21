package com.example.patientregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameField = findViewById(R.id.nameField);
        final EditText dateField = findViewById(R.id.dateField);
        final RadioGroup genderGroup = findViewById(R.id.genderRadioGroup);
        final Spinner maritalList = findViewById(R.id.maritalList);
        final EditText timeField = findViewById(R.id.timeField);
        final CheckBox smokingCheck = findViewById(R.id.smokingCheckBox);
        final CheckBox drinkingCheck = findViewById(R.id.drinkingCheckBox);
        final EditText ageField = findViewById(R.id.ageField);
        final EditText phoneField = findViewById(R.id.phoneField);
        final EditText addressField = findViewById(R.id.addrField);
        final Button clearButton = findViewById(R.id.resetButton);
        final Button submitButton = findViewById(R.id.submitButton);

        //Calendar instance for the DatePicker & TimePicker
        final Calendar calendar = Calendar.getInstance();

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //Date Picker Dialog
                DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateField.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        timeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);

                //Time Picker Dialog
                TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeField.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minutes, false);
                timePicker.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            //define event for onClick of Submit Button
            @Override
            public void onClick(View v) {
                String patientName = nameField.getText().toString();
                String patientDate = dateField.getText().toString();
                String patientGender = "Not Selected";

                int selectedId = genderGroup.getCheckedRadioButtonId();

                if(selectedId > 0){
                    RadioButton selectedRadioBtn = (RadioButton) findViewById(selectedId);
                    patientGender = selectedRadioBtn.getText().toString();
                }

                String patientMarital = maritalList.getSelectedItem().toString();
                String patientTime = timeField.getText().toString();
                String patientAddictions = "";

                if(smokingCheck.isChecked() && drinkingCheck.isChecked()){
                    patientAddictions += "Smoking, Drinking";
                }

                else if(drinkingCheck.isChecked()){
                    patientAddictions += "Drinking";
                }

                else if(smokingCheck.isChecked()){
                    patientAddictions += "Smoking";
                }

                else{
                    patientAddictions += "-NIL-";
                }

                String patientPhone = phoneField.getText().toString();
                String patientAddress = addressField.getText().toString();
                int patientAge = Integer.parseInt(ageField.getText().toString());

                //Transfer the contents to the SubmitActivity class using an Intent
                Intent submitIntent = new Intent(getApplicationContext(), SubmitActivity.class);
                submitIntent.putExtra("patientName", patientName);
                submitIntent.putExtra("patientDate", patientDate);
                submitIntent.putExtra("patientGender", patientGender);
                submitIntent.putExtra("patientMarital", patientMarital);
                submitIntent.putExtra("patientTime", patientTime);
                submitIntent.putExtra("patientAddictions", patientAddictions);
                submitIntent.putExtra("patientAge", patientAge);
                submitIntent.putExtra("patientPhone", patientPhone);
                submitIntent.putExtra("patientAddress", patientAddress);

                startActivity(submitIntent);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            //define event for onClick of Clear All Button
            @Override
            public void onClick(View v) {
                nameField.setText("");
                dateField.setText("");
                genderGroup.clearCheck();
                maritalList.setSelection(0);
                timeField.setText("");
                smokingCheck.setChecked(false);
                drinkingCheck.setChecked(false);
                ageField.setText("");
                addressField.setText("");
                phoneField.setText("");
            }
        });

    }
}