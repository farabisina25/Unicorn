package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfilesSubmenu extends AppCompatActivity {
    String userName;
    ImageButton homeBtn;
    Button searchBtn;
    AutoCompleteTextView actextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_submenu);

        homeBtn = findViewById(R.id.imageButton2);
        searchBtn = findViewById(R.id.button10);
        actextView = findViewById(R.id.autoCompleteTextView);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = actextView.getText().toString();
            }
        });
    }
}