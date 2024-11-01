package com.example.banksapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Languages extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_LANGUAGE = "language";

    private TextView labelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Загрузка выбранного языка из SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String languageCode = prefs.getString(KEY_LANGUAGE, "en"); // По умолчанию английский
        updateLocale(languageCode);

        setContentView(R.layout.activity_languages);

        labelText = findViewById(R.id.LabelText);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String newLanguageCode = "en"; // По умолчанию английский

            if (checkedId == R.id.radioButtonEnglish) {
                newLanguageCode = "en";
            } else if (checkedId == R.id.radioButtonRussian) {
                newLanguageCode = "ru";
            } else if (checkedId == R.id.radioButtonFrench) {
                newLanguageCode = "fr";
            } else if (checkedId == R.id.radioButtonKazakh) {
                newLanguageCode = "kk";
            }

            // Сохранение выбранного языка в SharedPreferences
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_LANGUAGE, newLanguageCode);
            editor.apply();

            // Обновление локали и UI
            updateLocale(newLanguageCode);
            updateUi();
        });
    }

    public void goBack(View view) {
        Intent intent = new Intent(Languages.this, Settings.class);
        startActivity(intent);
    }

    private void updateLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(config, dm);
    }

    private void updateUi() {

        labelText.setText(R.string.language);


    }
}