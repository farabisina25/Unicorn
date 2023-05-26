package com.example.unicorn;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    private final int GALLERY_REC_CODE = 1000;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference Profiles;
    String Email;
    String Description;
    String NameSurname;
    String BirthDate;
    String Department;
    String Telno;
    ImageView imageview;
    TextView textview1;
    TextView textview2;
    TextView textview3;

    TextView textview4;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    ImageButton homepagebutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Profiles = firestore.collection("Profiles");

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
        editText5 = findViewById(R.id.telno);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        homepagebutton  =findViewById(R.id.imageButton);

        isProfileCreated();
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
                if(button5.getText().toString().equals("Edit")){
                    setDescription(editText1.getText().toString());
                    setNameSurname(editText2.getText().toString());
                    setBirth(editText3.getText().toString());
                    setDepartment(editText4.getText().toString());
                    setTelno(editText5.getText().toString());

                    docRef = firestore.collection("Profiles").document(user.getUid());
                    docRef.update("Description", Description);
                    docRef.update("NameLastname", NameSurname);
                    docRef.update("Birth", BirthDate);
                    docRef.update("Department", Department);
                    docRef.update("Telno", Telno);
                }
                    setDescription(editText1.getText().toString());
                    setNameSurname(editText2.getText().toString());
                    setBirth(editText3.getText().toString());
                    setDepartment(editText4.getText().toString());
                    setTelno(editText5.getText().toString());

                    Map<String,Object> profile = new HashMap<>();
                    profile.put("Email" , Email);
                    profile.put("Description" , Description);
                    profile.put("NameLastname" , NameSurname);
                    profile.put("Birth" , BirthDate);
                    profile.put("Department" , Department);
                    profile.put("Telno" , Telno);
                    profile.put("ID" , user.getUid());
                    profile.put("ShowProfile" , "");

                    Profiles.document(user.getUid()).set(profile);

                    isProfileCreated();
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
        docRef = firestore.collection("Profiles").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    NameSurname = documentSnapshot.getData().get("NameLastname").toString();
                    Description = documentSnapshot.getData().get("Description").toString();
                    BirthDate = documentSnapshot.getData().get("Birth").toString();
                    Department = documentSnapshot.getData().get("Department").toString();
                    Telno = documentSnapshot.getData().get("Telno").toString();

                    editText1.setText(Description);
                    editText2.setText(NameSurname);
                    editText3.setText(BirthDate);
                    editText4.setText(Department);
                    editText5.setText(Telno);

                    button5.setText("Edit");
                }
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

    public void setTelno(String x){
        Telno = x;
    }
}