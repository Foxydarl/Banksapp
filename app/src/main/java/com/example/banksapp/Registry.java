package com.example.banksapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.example.banksapp.Models.DatabaseHelper;
import com.example.banksapp.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;

public class Registry extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        TextView signUpText = findViewById(R.id.textsignup);

        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        EditText email = findViewById(R.id.signupemail);
        EditText password = findViewById(R.id.signuppassword);
        EditText name = findViewById(R.id.signupname);
        EditText phone = findViewById(R.id.signupphone);
        Button signup = findViewById(R.id.buttonsignup);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registry.this, SignIn.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " + email);
                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(v, "Введите E-mail", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    Snackbar.make(v, "Введите пароль", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().length() < 6){
                    Snackbar.make(v, "Пароль должен быть больше 6 символов", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(v, "Введите имя", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(v, "Введите телефон", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    Snackbar.make(v, "Введите корректный E-mail", Snackbar.LENGTH_LONG).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = authResult.getUser(); // Get FirebaseUser from AuthResult
                                if (user != null) {
                                    String uid = user.getUid(); // Get UID
                                    User userInfo = new User(name.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString());
                                    users.child(uid).setValue(userInfo) // Use UID as the path
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Snackbar.make(v, "Регистрация прошла успешно", Snackbar.LENGTH_LONG).show();
                                                    /*DatabaseHelper dbhelper = new DatabaseHelper();
                                                    dbhelper.addUser(name.getText().toString(), email.getText().toString(), phone.getText().toString(), password.getText().toString());*/
                                                }
                                            });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "createUserWithEmailAndPassword failed", e);
                                Snackbar.make(v, "Ошибка регистрации: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                            }
                        });


            }
        });
    }
}