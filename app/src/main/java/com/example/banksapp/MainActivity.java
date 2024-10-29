package com.example.banksapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        };

    public void onNextClick(View view) {
    View view1 = findViewById(R.id.indicator1);
    View view2 = findViewById(R.id.indicator2);
    View view3 = findViewById(R.id.indicator3);
    if (view1.getWidth() == 16){
        ViewGroup.LayoutParams view1params= view1.getLayoutParams();
        view1params.width = 8;

        }


    }
    }