package com.example.mvp.androidmvparchitectureexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.ui.login.LoginActivity;

public class ScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }, 0);
//        3000
    }
}