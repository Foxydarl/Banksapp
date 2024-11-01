package com.example.banksapp.Models;


public class User {
    private String name;
    private String phone;
    private String password;
    private String email;

    // Пустой конструктор для Firestore
    public User() {}

    public User(String name, String phone, String password, String email) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
