package com.example.banksapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banksapp.Models.FirestoreHelper;
import com.example.banksapp.Models.User;

import java.util.List;


public class Profile extends AppCompatActivity {

    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_profile);
        TextView name = findViewById(R.id.profileName);

        firestoreHelper = new FirestoreHelper();

        firestoreHelper.getUsers(new FirestoreHelper.FirestoreCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> userList) {
                for (User user : userList) {
                    name.setText(user.getName());
                }
            }
            @Override
            public void onFailure(Exception e) {
                Log.e("FirestoreError", "Error fetching users", e);
            }

        });


        };
    public void toSettings(View view){
        Intent intent = new Intent(Profile.this, Settings.class);
        startActivity(intent);
    }
    public void toCards(View view){
        Intent intent = new Intent(Profile.this, AllCards.class);
        startActivity(intent);
    }

}


