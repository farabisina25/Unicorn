package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookInfo extends AppCompatActivity {
    FirebaseFirestore firestore;
    String course;
    String name;
    String author;
    String price;
    String comments;
    String type;

    RadioButton radioButton17;
    RadioButton radioButton18;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    EditText editText9;
    EditText editText10;
    ImageButton profileButton;
    Button createButton;
    Button uploadButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        firestore = FirebaseFirestore.getInstance();

        radioButton17 = findViewById(R.id.radioButton17);
        radioButton18 = findViewById(R.id.radioButton18);

        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);

        profileButton = findViewById(R.id.profileBtn);
        createButton = findViewById(R.id.createBtn);
        uploadButton = findViewById(R.id.uploadBtn);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCourse(editText6.getText().toString());
                setName(editText7.getText().toString());
                setAuthor(editText8.getText().toString());
                setPrice(editText9.getText().toString());
                setComments(editText10.getText().toString());
                setBookType();

                Map<String,Object> book = new HashMap<>();
                book.put("Type" , type);
                book.put("Course" , course);
                book.put("Name" , name);
                book.put("Author" , author);
                book.put("Price" , price);
                book.put("Comments" , comments);

                firestore.collection("Books").add(book);
            }
        });
    }

    public void setBookType(){
        String x = "";
        if(radioButton17.callOnClick()){
            x = "Reading Book";
        }
        else if(radioButton18.callOnClick()){
            x = "Lecture Book";
        }
        type = x;
    }

    public void setCourse(String x){
        course = x;
    }

    public void setName(String x){
        name = x;
    }

    public void setAuthor(String x){
        author = x;
    }

    public void setPrice(String x){
        price = x;
    }

    public void setComments(String x){
        comments = x;
    }

    public void uploadPhoto(){

    }
}