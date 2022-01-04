package com.example.labpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final EditText urlText = findViewById(R.id.urlText);
        final Button goButton = findViewById(R.id.goButton);
        final WebView webView = findViewById(R.id.webView);

        ActivityCompat.requestPermissions(WebActivity.this, new String[]{
                Manifest.permission.INTERNET
        }, 1);

        //Enable JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlText.getText().toString();
                webView.loadUrl(url);

                //Use intent to load the site, instead of WebView
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
            }
        });
    }
}