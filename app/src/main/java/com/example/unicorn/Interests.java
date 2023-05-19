package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class Interests extends AppCompatActivity {

    Button profileBtn;

    ScrollView scrollView1;
    ScrollView scrollView2;
    ScrollView scrollView3;
    ScrollView scrollView4;
    ScrollView scrollView5;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        profileBtn = findViewById(R.id.profileBtn2);

        scrollView1 = findViewById(R.id.scrollView1);
        scrollView2 = findViewById(R.id.scrollView2);
        scrollView3 = findViewById(R.id.scrollView3);
        scrollView4 = findViewById(R.id.scrollView4);
        scrollView5 = findViewById(R.id.scrollView5);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setMusics(){

    }

    public void setSports(){

    }

    public void setCourses(){

    }

    public void setBooks(){

    }

    public void schoolClubs(){

    }
}