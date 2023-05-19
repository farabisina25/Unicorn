package com.example.unicorn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    //Comment
    private final int GALLERY_REC_CODE = 1000;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference reference;
    Query checkUser;
    String Email;
    String Description;
    String NameSurname;
    String BirthDate;
    String Department;

    ImageView imageview;
    TextView textview1;
    TextView textview2;
    TextView textview3;

    TextView textview4;
    EditText editText1;
    EditText editText2;
    EditText editText3;

    EditText editText4;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    ImageButton homepagebutton;
    String emailFromDB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Profiles");

        Email = user.getEmail();

        imageview = findViewById(R.id.imageView);
        textview1 = findViewById(R.id.textView1);
        textview2 = findViewById(R.id.textView2);
        textview3 = findViewById(R.id.textView3);
        textview4 = findViewById(R.id.textView4);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        homepagebutton  =findViewById(R.id.imageButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , RoommatesInfo.class);
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , BookInfo.class);
                startActivity(intent);
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Interests.class);
                startActivity(intent);
                finish();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Activity.class);
                startActivity(intent);
                finish();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setDescription(editText1.getText().toString());
                    setNameSurname(editText2.getText().toString());
                    setBirth(editText3.getText().toString());
                    setDepartment(editText4.getText().toString());

                    Map<String,Object> profile = new HashMap<>();
                    profile.put("Email" , Email);
                    profile.put("Description" , Description);
                    profile.put("NameLastname" , NameSurname);
                    profile.put("Birth" , BirthDate);
                    profile.put("Department" , Department);

                    firestore.collection("Profiles").add(profile);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REC_CODE);
            }
        });

        homepagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
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

    public void isProfileCreated(){
        checkUser = reference.orderByChild("Email").equalTo(Email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    emailFromDB = snapshot.child(Email).child("Email").getValue(String.class);
                    if(emailFromDB.equals(Email)){
                        Email = emailFromDB;
                        Description = snapshot.child(Email).child("Description").getValue(String.class);
                        NameSurname = snapshot.child(Email).child("NameSurname").getValue(String.class);
                        BirthDate = snapshot.child(Email).child("BirthDate").getValue(String.class);
                        Department = snapshot.child(Email).child("Department").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext() , HomePage.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    emailFromDB = null;
            }

        });
    }
    public void setDescription(String x){
        Description = x;
    }

    public void setNameSurname(String x){
        NameSurname = x;
    }

    public void setBirth(String x){
        BirthDate = x;
    }

    public void setDepartment(String x){
        Department = x;
    }

    public void showProfile(){

    }

}