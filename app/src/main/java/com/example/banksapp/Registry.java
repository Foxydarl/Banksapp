package com.example.banksapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;

import com.example.banksapp.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registry extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        TextView signUpText = findViewById(R.id.textsignup);

        EditText passwordEditText = findViewById(R.id.signuppassword);

        passwordEditText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int drawableEndPosition = passwordEditText.getWidth() - passwordEditText.getPaddingEnd();
                if (event.getRawX() >= (drawableEndPosition - passwordEditText.getCompoundDrawables()[2].getBounds().width())) {
                    if (isPasswordVisible) {
                        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_eye, 0);
                    } else {
                        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock, 0, R.drawable.ic_eye_off, 0);
                    }
                    isPasswordVisible = !isPasswordVisible;
                    passwordEditText.setSelection(passwordEditText.getText().length());
                    return true;
                }
            }
            return false;
        });

        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance(); // Initialize Firestore

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
                                FirebaseUser user = authResult.getUser();
                                if (user != null) {
                                    String uid = user.getUid();
                                    User userInfo = new User(name.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString());
                                    // Сохранение в Firestore
                                    firestore.collection("Users").document(uid).set(userInfo)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("TAG", "User data added to Firestore successfully");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.e("TAG", "Failed to add user data to Firestore", e);
                                                    Snackbar.make(v, "Ошибка сохранения в Firestore: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
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
