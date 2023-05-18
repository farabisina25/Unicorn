package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    //Comment
    FirebaseFirestore firestore;
    String Description;
    String NameSurname;
    String BirthDate;
    String Department;
    String Hometown;

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

    Boolean isProfileCreated;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firestore = FirebaseFirestore.getInstance();
        imageview = findViewById(R.id.imageView);
        textview1 = findViewById(R.id.textView1);
        textview2 = findViewById(R.id.textView2);
        textview3 = findViewById(R.id.textView3);
        textview4 = findViewById(R.id.textView4);
        editText1 = findViewById(R.id.editTextText1);
        editText2 = findViewById(R.id.editTextText2);
        editText3 = findViewById(R.id.editTextText3);
        editText4 = findViewById(R.id.editTextText4);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        homepagebutton  =findViewById(R.id.imageButton);
        isProfileCreated = false;

        if(isProfileCreated){
            button5.setText("Save");
            editText1.setText(Description);
            editText2.setText(NameSurname);
            editText3.setText(Department);
            editText4.setText(Hometown);
        }

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
                if (isProfileCreated = false) {
                    setDescription(editText1.getText().toString());
                    setNameSurname(editText2.getText().toString());
                    setDepartment(editText3.getText().toString());
                    setHometown(editText4.getText().toString());

                    Map<String,Object> profile = new HashMap<>();
                    profile.put("Description" , Description);
                    profile.put("NameLastname" , NameSurname);
                    profile.put("Department" , Department);
                    profile.put("HomeTown" , Hometown);

                    firestore.collection("Profiles").add(profile);
                    isProfileCreated = true;
                }
                else{
                    setDescription(editText1.getText().toString());
                    setNameSurname(editText2.getText().toString());
                    setDepartment(editText3.getText().toString());
                    setHometown(editText4.getText().toString());
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public void uploadPhoto(){

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

    public void setHometown(String x){
        Hometown = x;
    }

    public void showProfile(){

    }

}