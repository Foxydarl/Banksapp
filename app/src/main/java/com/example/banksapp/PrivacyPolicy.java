package com.example.banksapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_privacy_policy);

        };

    public void goBack(View view){
        Intent intent = new Intent(PrivacyPolicy.this, Settings.class);
        startActivity(intent);
        }
    }

