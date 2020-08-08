package com.saathiral.removechinaapps;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView app_icon;
    private TextView app_name, welcome_message, welcome_message2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialViews();
        callMainScreen();
    }

    private void initialViews() {
        app_icon = findViewById(R.id.app_icon);
        app_name = findViewById(R.id.app_name);
        welcome_message = findViewById(R.id.welcome_message);
        welcome_message2 = findViewById(R.id.welcome_message2);
        //welcome_message2.setTypeface(Typeface.createFromAsset(getAssets(), "ProductSans-Bold.ttf"));
    }

    private void callMainScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }

}