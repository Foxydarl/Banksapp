package com.example.banksapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.banksapp.Models.PrivacyPolicy;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
        RadioButton settingsButton = findViewById(R.id.settings_button);
        settingsButton.setChecked(true);

        bottomNavigation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.home_button) {
                    Intent intent = new Intent(Settings.this, MainActivity.class);
                    startActivity(intent);
                } else if (checkedId == R.id.cards_button) {

                } else if (checkedId == R.id.stats_button) {

                } else if (checkedId == R.id.settings_button) {
                    Intent intent = new Intent(Settings.this, Settings.class);
                }
            }
        });
        };
    public void OpenProfile(View view) {
        Intent intent = new Intent(Settings.this, Profile.class);
        startActivity(intent);


    }
    public void toTerms(View view){
        Intent intent = new Intent(Settings.this, PrivacyPolicy.class);
        startActivity(intent);
    }

}
