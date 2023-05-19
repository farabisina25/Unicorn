package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;

public class Interests extends AppCompatActivity {

    ImageButton profileBtn;
    public String[] musicTypes = new String[]
            {
                    "Rock", "Pop", "R B", "Electronic"

            };

    public MultiAutoCompleteTextView editText;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);


        editText = findViewById(R.id.mactv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicTypes);
        editText.setAdapter(adapter);
        editText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        profileBtn = findViewById(R.id.imageButton10);

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