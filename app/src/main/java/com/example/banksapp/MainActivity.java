package com.example.banksapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE); // Измените эту строку
        boolean introSeen = sharedPreferences.getBoolean("introSeen", false); // Получение флага introSeen

        if (introSeen) {
            // Перейти на главную активность (например, Profile)
            Intent intent = new Intent(this, SignIn.class); // Замените Profile на имя вашей главной активности
            startActivity(intent);
            finish(); // Закрыть MainActivity
            return; // Прекратить выполнение onCreate()
        }
        };

    public void onNextClick(View view) {
    View view1 = findViewById(R.id.indicator1);
    View view2 = findViewById(R.id.indicator2);
    View view3 = findViewById(R.id.indicator3);
    ImageView imageView= findViewById(R.id.introView);
    TextView maintext = findViewById(R.id.mainText);
    TextView subtext = findViewById(R.id.subText);
    float density = getResources().getDisplayMetrics().density;
    int dpValue = (int) (view1.getWidth() / density + 0.5f);
    int view3Value = (int) (view3.getWidth() / density + 0.5f);
    if (view3Value == 16){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("introSeen", true); // Сохранение флага introSeen
        editor.apply();
        Intent intent = new Intent(MainActivity.this, SignIn.class);
        startActivity(intent);
    }
    else if (dpValue == 16){
        maintext.setText(getResources().getString(R.string.introtext1));
        subtext.setText(getResources().getString(R.string.introundertext1));
        imageView.setImageResource(R.drawable.introsecond);
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
    else if (dpValue == 8){
        maintext.setText(getResources().getString(R.string.introtext2));
        subtext.setText(getResources().getString(R.string.introundertext2));
        imageView.setImageResource(R.drawable.introthird);
        ViewGroup.LayoutParams view1params= view1.getLayoutParams();
        view1params.width = (int) (8 * density + 0.5f);
        view1.setLayoutParams(view1params);;
        view1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_unselected, null));
        ViewGroup.LayoutParams view2params= view2.getLayoutParams();
        view2params.width = (int) (8 * density + 0.5f);
        view2.setLayoutParams(view2params);
        view2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_unselected, null));
        ViewGroup.LayoutParams view3params= view3.getLayoutParams();
        view3params.width = (int) (16 * density + 0.5f);
        view3.setLayoutParams(view3params);
        view3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.indicator_selected, null));
    }



    }
}