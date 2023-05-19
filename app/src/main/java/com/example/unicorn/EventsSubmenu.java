package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class EventsSubmenu extends AppCompatActivity {
    String eventtype;
    ImageButton homepagebutton;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_submenu);

        homepagebutton = findViewById(R.id.imageButton2);

        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton3);
        rb3 = findViewById(R.id.radioButton4);

        homepagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
            setType();
        }
    }

    public void setType(){
        if(rb1.isChecked()){
            eventtype = "Concert";
        }
        else if(rb2.isChecked()){
            eventtype = "Theatre";
        }
        else if(rb3.isChecked()){
            eventtype = "Party/Festival";
        }
    }
}