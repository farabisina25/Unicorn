package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfilesSubmenu extends AppCompatActivity {
    String userName;
    ImageButton homeBtn;
    ImageButton profileBtn;
    Button searchBtn;
    AutoCompleteTextView actextView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_submenu);

        homeBtn = findViewById(R.id.homeBtn);
        searchBtn = findViewById(R.id.button10);
        profileBtn = findViewById(R.id.imageButton6);

        textView1 = findViewById(R.id.textView43);
        textView2 = findViewById(R.id.textView44);
        textView3 = findViewById(R.id.textView45);
        textView4 = findViewById(R.id.textView46);
        textView5 = findViewById(R.id.textView47);
        textView6 = findViewById(R.id.textView48);
        textView7 = findViewById(R.id.textView49);

        actextView = findViewById(R.id.autoCompleteTextView);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = actextView.getText().toString();
                if(userName != null){
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);

                    profileBtn.setVisibility(View.VISIBLE);
                    profileBtn.setClickable(true);
                }
            }
        });
    }
}