package com.example.homeserviceapp.serviceactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.homeserviceapp.R;

public class allserviceActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allservice);
        getWindow().setStatusBarColor(ContextCompat.getColor(allserviceActivity.this, R.color.white));


        //Objects.requireNonNull(getSupportActionBar()).setTitle("All Services");
      // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
}