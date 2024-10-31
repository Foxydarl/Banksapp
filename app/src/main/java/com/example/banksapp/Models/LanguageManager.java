package com.example.banksapp.Models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LanguageManager {

    private static final String PREFS_NAME = "app_language";
    private static final String KEY_LANGUAGE = "language";

    private Context context;

    public LanguageManager(Context context) {
        this.context = context;
    }

    // Сохранение выбранного языка в SharedPreferences
    public void setLocale(String languageCode) {
        saveLanguage(languageCode);
        updateResources(languageCode);
    }

    // Получение сохраненного языка
    public String getLanguage() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LANGUAGE, Locale.getDefault().getLanguage());
    }

    // Сохранение кода языка в SharedPreferences
    private void saveLanguage(String languageCode) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LANGUAGE, languageCode);
        editor.apply();
    }

    // Обновление ресурсов с новым языком
    private void updateResources(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        // Перезапуск активности для применения изменений (если context - это Activity)
        if (context instanceof Activity) {
            Intent intent = ((Activity) context).getIntent();
            ((Activity) context).finish();
            context.startActivity(intent);
        }
    }
}
