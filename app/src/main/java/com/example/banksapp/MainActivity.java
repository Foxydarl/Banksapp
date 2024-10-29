package com.example.banksapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        };
    public void onNextClick(View view){
        Intent intent = new Intent(MainActivity.this, SignIn.class);
        startActivity(intent);
    }
    /*public void onNextClick(View view) {
    View view1 = findViewById(R.id.indicator1);
    View view2 = findViewById(R.id.indicator2);
    View view3 = findViewById(R.id.indicator3);
    float density = getResources().getDisplayMetrics().density;
    int dpValue = (int) (view1.getWidth() / density + 0.5f);
    if (dpValue == 16){
        ViewGroup.LayoutParams view1params= view1.getLayoutParams();
        view1params.width = (int) (8 * density + 0.5f);
        view1.setLayoutParams(view1params);;
        view1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_unselected, null));
        ViewGroup.LayoutParams view2params= view2.getLayoutParams();
        view2params.width = (int) (16 * density + 0.5f);
        view2.setLayoutParams(view2params);
        view2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_selected, null));
        ViewGroup.LayoutParams view3params= view3.getLayoutParams();
        view3params.width = (int) (8 * density + 0.5f);
        view3.setLayoutParams(view3params);
        view3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_unselected, null));
        }


    }*/
    }