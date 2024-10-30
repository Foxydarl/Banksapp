package com.example.banksapp.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы при создании базы данных
        String createTable = "CREATE TABLE Users (name TEXT, email TEXT, password TEXT, phone TEXT, image BLOB)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Логика обновления базы данных при изменении версии
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public void addUser(String name, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("password", password);

        long result = db.insert("Users", null, values);
        if(result == -1){
            Log.d("SQLite", "Ошибка вставки данных");
        } else {
            Log.d("SQLite", "Данные успешно вставлены, ID: " + result);
        }
    }

    public void updateUserPassword(String oldPassword, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", newPassword);

        int rowsAffected = db.update("Users", values, "password=?", new String[]{oldPassword});
        if (rowsAffected > 0) {
            Log.d("SQLite", "Обновление успешно, обновлено строк: " + rowsAffected);
        } else {
            Log.d("SQLite", "Ошибка обновления данных");
        }
    }


}