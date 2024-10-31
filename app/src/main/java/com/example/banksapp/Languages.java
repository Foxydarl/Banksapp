package com.example.banksapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Languages extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioen) {
                    changeLanguage("en");
                } else if (checkedId == R.id.radioru) {
                    changeLanguage("ru");
                } else if (checkedId == R.id.radiofr) {
                    changeLanguage("fr");
                } else if (checkedId == R.id.radiokz) {
                    changeLanguage("kk");
                }
            }
        });
    }

    public void goBack(View view) {
        Intent intent = new Intent(Languages.this, Settings.class);
        startActivity(intent);
    }

    private void setDefaultRadioButton(String languageCode) {
        switch (languageCode) {
            case "en":
                radioGroup.check(R.id.radioen);
                break;
            case "ru":
                radioGroup.check(R.id.radioru);
                break;
            case "fr":
                radioGroup.check(R.id.radiofr);
                break;
            case "kk":
                radioGroup.check(R.id.radiokz);
                break;
            default:
                radioGroup.check(R.id.radioen); // По умолчанию английский, если язык системы не совпадает с доступными
                break;
        }
    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Перезапуск активности для применения изменений
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
