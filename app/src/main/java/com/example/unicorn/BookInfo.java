package com.example.unicorn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookInfo extends AppCompatActivity {
    FirebaseFirestore firestore;
    private final int GALLERY_REC_CODE = 1000;
    String course;
    String name;
    String author;
    String price;
    String comments;
    String type;
    ImageView imageview;
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

        imageview = findViewById(R.id.imageView5);

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

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REC_CODE);
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
                if(radioButton17.isChecked()){type = "Reading Book";}
                else if(radioButton18.isChecked()){type = "Lecture Book";}

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REC_CODE){
                imageview.setImageURI(data.getData());
            }
        }
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