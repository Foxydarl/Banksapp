package com.example.banksapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class Registry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        TextView signUpText = findViewById(R.id.textsignup);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registry.this, SignIn.class);
                startActivity(intent);
            }
        });

    }
public void onSignup(View view) {
    Intent intent = new Intent(this, Settings.class);
    startActivity(intent);
    }
}