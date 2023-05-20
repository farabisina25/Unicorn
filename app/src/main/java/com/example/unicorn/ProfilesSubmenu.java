package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ProfilesSubmenu extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference Books;
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
    String[] profiles = new String[50];
    String NameLastname;
    String Department;
    String Description;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_submenu);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Books = firestore.collection("Profiles");

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

        setProfiles();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profiles);
        actextView.setAdapter(adapter);


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

                    getProfiles(userName);
                }
            }
        });
    }

    public void setProfiles(){
        firestore.collection("Profiles")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i= 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    profiles[i] = document.getData().get("NameLastname").toString();
                                    i++;
                                }
                            }
                        }
                    }
                });
        for(int i = 0 ; i< 50 ; i++){
            if(profiles[i] == null){
                profiles[i] = "!";
            }
        }
    }

    public void getProfiles(String name){
        firestore.collection("Profiles")
                .whereEqualTo("NameLastname", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                docRef = firestore.collection("Profiles").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            NameLastname = documentSnapshot.getData().get("NameLastname").toString();
                                            Department = documentSnapshot.getData().get("Department").toString();
                                            Description = documentSnapshot.getData().get("Description").toString();

                                            textView4.setText(NameLastname);
                                            textView5.setText(Department);
                                            textView6.setText(Description);
                                            textView7.setText(NameLastname);
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
    }
}