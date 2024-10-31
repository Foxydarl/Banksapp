package com.example.banksapp.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class UserDataStorage {

    private SharedPreferences encryptedSharedPreferences;
    private Context context;
    private MasterKey masterKey;
    private String userId; // Уникальный идентификатор пользователя

    public UserDataStorage(Context context, String userId) throws GeneralSecurityException, IOException {
        this.context = context;
        this.userId = userId;

        // Создаем MasterKey для шифрования
        masterKey = new MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        // Создаем EncryptedSharedPreferences для хранения текстовых данных
        encryptedSharedPreferences = EncryptedSharedPreferences.create(
                context,
                "EncryptedPrefs_" + userId, // Используем уникальный идентификатор в названии
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }

    // Функции для сохранения данных

    public void saveUsername(String username) {
        encryptedSharedPreferences.edit().putString("username", username).apply();
    }

    public void savePhone(String phone) {
        encryptedSharedPreferences.edit().putString("phone", phone).apply();
    }

    public void savePassword(String password) {
        encryptedSharedPreferences.edit().putString("password", password).apply();
    }

    public void saveEmail(String email) {
        encryptedSharedPreferences.edit().putString("email", email).apply();
    }

    public void savePhoto(Bitmap bitmap) throws IOException, GeneralSecurityException {
        File file = new File(context.getFilesDir(), "encryptedPhoto_" + userId + ".jpg");
        EncryptedFile encryptedFile = new EncryptedFile.Builder(
                context, // Context первым
                file,    // Затем файл
                masterKey,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        FileOutputStream outputStream = encryptedFile.openFileOutput();
        outputStream.write(byteArray);
        outputStream.close();
    }

    // Функции для получения данных

    public String getUsername() {
        return encryptedSharedPreferences.getString("username", null);
    }

    public String getPhone() {
        return encryptedSharedPreferences.getString("phone", null);
    }

    public String getPassword() {
        return encryptedSharedPreferences.getString("password", null);
    }

    public String getEmail() {
        return encryptedSharedPreferences.getString("email", null);
    }

    public Bitmap getPhoto() throws IOException, GeneralSecurityException {
        File file = new File(context.getFilesDir(), "encryptedPhoto_" + userId + ".jpg");
        if (!file.exists()) {
            return null; // Возвращаем null, если фото не найдено
        }

        EncryptedFile encryptedFile = new EncryptedFile.Builder(
                context, // Context первым
                file,    // Затем файл
                masterKey,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build();

        FileInputStream inputStream = encryptedFile.openFileInput();
        byte[] byteArray = new byte[(int) file.length()];
        inputStream.read(byteArray);
        inputStream.close();

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    // Очистка данных при выходе
    public void clearUserData() {
        encryptedSharedPreferences.edit().clear().apply();
        File file = new File(context.getFilesDir(), "encryptedPhoto_" + userId + ".jpg");
        if (file.exists()) {
            file.delete();
        }
    }
}
