package com.example.banksapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseApp;

public class SignIn extends AppCompatActivity {

    FirebaseAuth auth;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Перейти на главную активность
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
            finish(); // Закрыть стартовую активность
        }
        setContentView(R.layout.activity_sign_in);
        TextView signUpText = findViewById(R.id.textsignin);

        EditText passwordEditText = findViewById(R.id.signinpassword);

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

        EditText email = findViewById(R.id.signinemail);
        EditText password = findViewById(R.id.signinpassword);
        Button signin = findViewById(R.id.buttonsignin);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, Registry.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " + email.getText().toString());
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
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    Snackbar.make(v, "Введите корректный E-mail", Snackbar.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Snackbar.make(v, "Вход выполнен успешно", Snackbar.LENGTH_LONG).show();
                                // В активности входа после успешной аутентификации
                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLoggedIn", true);
                                editor.apply();
                                startActivity(new Intent(SignIn.this, Profile.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "createUserWithEmailAndPassword failed", e);
                                Snackbar.make(v, "Ошибка входа: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                            }
                        });


            }
        });

    }
    public void adminbutton(View view) {
        Intent intent = new Intent(SignIn.this, Settings.class);
        startActivity(intent);
    }
}