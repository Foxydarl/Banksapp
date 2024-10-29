package com.example.banksapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
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
    ImageView imageView= findViewById(R.id.introView);
    TextView maintext = findViewById(R.id.mainText);
    TextView subtext = findViewById(R.id.subText);
    float density = getResources().getDisplayMetrics().density;
    int dpValue = (int) (view1.getWidth() / density + 0.5f);
    int view3Value = (int) (view3.getWidth() / density + 0.5f);
    if (view3Value == 16){
        Intent intent = new Intent(this, SignIn.class);
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