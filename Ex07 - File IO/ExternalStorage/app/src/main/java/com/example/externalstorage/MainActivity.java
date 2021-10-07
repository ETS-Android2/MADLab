package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText writeFileText = findViewById(R.id.writeFileText);
        final EditText readFileText = findViewById(R.id.readFileText);
        final EditText writeContentsText = findViewById(R.id.writeContentsText);
        final TextView readContentsText = findViewById(R.id.readContentsText);
        final Button readButton = findViewById(R.id.readButton);
        final Button writeButton = findViewById(R.id.writeButton);
        final File sdCard = Environment.getExternalStorageDirectory();

        //Request for the read/write permissions to the external storage from the user
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String writeFileName = writeFileText.getText().toString();
                String writeContents = writeContentsText.getText().toString();

                //File Write Logic
                try {
                    File file = new File(getExternalFilesDir(null), writeFileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(writeContents.getBytes());
                    fileOutputStream.close();

                    Toast successToast = Toast.makeText(MainActivity.this, "File Written Successfully!", Toast.LENGTH_SHORT);
                    successToast.show();
                } catch (Exception e) {
                    Toast errorToast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String readFileName = readFileText.getText().toString();

                //File Read Logic
                try{
                    String buffer, readContents = "";
                    File file = new File(getExternalFilesDir(null), readFileName);
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    while((buffer = bufferedReader.readLine()) != null){
                        //Read till the bufferedReader gets characters
                        readContents += buffer;
                    }

                    readContentsText.setText(readContents);

                    Toast successToast = Toast.makeText(MainActivity.this, "File Read Successfully!", Toast.LENGTH_SHORT);
                    successToast.show();

                } catch (Exception e) {
                    Toast errorToast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            }
        });
    }
}