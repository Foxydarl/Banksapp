package com.example.banksapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banksapp.Models.User;


public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        TextView name = findViewById(R.id.profileName);
        name.setText(User.getName());

        };
    public void toSettings(View view){
        Intent intent = new Intent(Profile.this, Settings.class);
        startActivity(intent);
    }

}


