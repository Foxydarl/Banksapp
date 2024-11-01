package com.example.banksapp.Models;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Инициализация сохраненного языка при запуске приложения
        LanguageManager languageManager = new LanguageManager(this);
        languageManager.setLocale(languageManager.getLanguage());
    }
}