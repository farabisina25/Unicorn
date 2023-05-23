package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RoommatesSubmenu extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference IDs;
    int[] counts;
    String[] ids;
    String[] IDArray;
    String name1;
    String name2;
    String name3;
    String name4;
    int[] CountArray;
    TextView[] textViews;
    TextView[] textViews2;
    ImageButton homepagebutton;
    ImageButton profile1;
    ImageButton profile2;
    ImageButton profile3;
    ImageButton profile4;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    Button but1;
    Button but2;
    Button but3;
    String gender;
    String campus;
    String workintheroom;
    String smoke;
    String cook;
    String instrument;
    String sleeplight;
    String roommatecount;
    String sleeptime;
    String getuptime;
    String ID;
    String gender2;
    String campus2;
    String workintheroom2;
    String smoke2;
    String cook2;
    String instrument2;
    String sleeplight2;
    String roommatecount2;
    String sleeptime2;
    String getuptime2;
    int i;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommates_submenu);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        IDs = firestore.collection("IDs");
        homepagebutton = findViewById(R.id.homeBtn);
        profile1 = findViewById(R.id.btn1);
        profile2 = findViewById(R.id.btn2);
        profile3 = findViewById(R.id.btn3);
        profile4 = findViewById(R.id.btn4);
        ID = user.getUid();
        IDArray = new String[4];
        CountArray = new int[4];
        counts = new int[50];
        ids = new String[50];
        textView1 = findViewById(R.id.t1);
        textView2 = findViewById(R.id.t2);
        textView3 = findViewById(R.id.t3);
        textView4 = findViewById(R.id.t4);
        textView5 = findViewById(R.id.t5);
        textView6 = findViewById(R.id.t6);
        textView7 = findViewById(R.id.t7);
        textView8 = findViewById(R.id.t8);
        textViews = new TextView[4];
        textViews2 = new TextView[4];
        textViews[0] = textView1;
        textViews[1] = textView3;
        textViews[2] = textView5;
        textViews[3] = textView7;
        textViews2[0] = textView2;
        textViews2[1] = textView4;
        textViews2[2] = textView6;
        textViews2[3] = textView8;
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        ArrayList<String> NameLastnames = new ArrayList<>();
        Arrays.fill(counts, 0);

        firestore.collection("RoommateInfos").whereEqualTo("ID", ID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()){
                        docRef = firestore.collection("RoommateInfos").document(document.getId());
                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    gender = documentSnapshot.getData().get("Gender").toString();
                                    campus = documentSnapshot.getData().get("Campus").toString();
                                    workintheroom = documentSnapshot.getData().get("WorkPlace").toString();
                                    smoke = documentSnapshot.getData().get("Smoke").toString();
                                    cook = documentSnapshot.getData().get("Cook").toString();
                                    instrument = documentSnapshot.getData().get("Instrument").toString();
                                    sleeplight = documentSnapshot.getData().get("Sleep Light").toString();
                                    roommatecount = documentSnapshot.getData().get("Roommate Count").toString();
                                    sleeptime = documentSnapshot.getData().get("Sleep Time").toString();
                                    getuptime = documentSnapshot.getData().get("Get Up Time").toString();
                                }
                            }
                        });

                    }
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<QuerySnapshot> task) {
                firestore.collection("RoommateInfos").whereNotEqualTo("ID", ID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                docRef = firestore.collection("RoommateInfos").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()) {
                                            int count = 0;
                                            gender2 = documentSnapshot.getData().get("Gender").toString();
                                            campus2 = documentSnapshot.getData().get("Campus").toString();
                                            workintheroom2 = documentSnapshot.getData().get("WorkPlace").toString();
                                            smoke2 = documentSnapshot.getData().get("Smoke").toString();
                                            cook2 = documentSnapshot.getData().get("Cook").toString();
                                            instrument2 = documentSnapshot.getData().get("Instrument").toString();
                                            sleeplight2 = documentSnapshot.getData().get("Sleep Light").toString();
                                            roommatecount2 = documentSnapshot.getData().get("Roommate Count").toString();
                                            sleeptime2 = documentSnapshot.getData().get("Sleep Time").toString();
                                            getuptime2 = documentSnapshot.getData().get("Get Up Time").toString();
                                            ids[i] = documentSnapshot.getData().get("ID").toString();
                                            if (campus.equals(campus2)) {
                                                count++;
                                            }
                                            if (workintheroom.equals(workintheroom2)) {
                                                count++;
                                            }
                                            if (smoke.equals(smoke2)) {
                                                count++;
                                            }
                                            if (cook.equals(cook2)) {
                                                count++;
                                            }
                                            if (instrument.equals(instrument2)) {
                                                count++;
                                            }
                                            if (sleeplight.equals(sleeplight2)) {
                                                count++;
                                            }
                                            if (roommatecount.equals(roommatecount2)) {
                                                count++;
                                            }
                                            if (sleeptime.equals(sleeptime2)) {
                                                count++;
                                            }
                                            if (getuptime.equals(getuptime2)) {
                                                count++;
                                            }
                                            if (!gender.equals(gender2)) {
                                                count = 0;
                                            }
                                            counts[i] = count;
                                            i++;
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 4; i++) {
                    int max = 0;
                    int position = 0;
                    for (int j = 0; j < counts.length; j++) {
                        if (counts[j] > max) {
                            max = counts[j];
                            position = j;
                        }
                    }
                    CountArray[i] = counts[position];
                    IDArray[i] = ids[position];
                    counts[position] = 0;
                }
                DocumentReference docRef = firestore.collection("Profiles").document(IDArray[0]);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name1 = document.getData().get("NameLastname").toString();
                            }
                        }
                    }
                });
                DocumentReference docRef2 = firestore.collection("Profiles").document(IDArray[1]);
                docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name2 = document.getData().get("NameLastname").toString();
                            }
                        }
                    }
                });
                DocumentReference docRef3 = firestore.collection("Profiles").document(IDArray[2]);
                docRef3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name3 = document.getData().get("NameLastname").toString();
                            }
                        }
                    }
                });
                DocumentReference docRef4 = firestore.collection("Profiles").document(IDArray[3]);
                docRef4.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                name4 = document.getData().get("NameLastname").toString();
                            }
                        }
                    }
                });
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(name1);
                textView2.setText("%" + (CountArray[0] * 10) + " Matching");
                textView3.setText(name2);
                textView4.setText("%" + (CountArray[1] * 10) + " Matching");
                textView5.setText(name3);
                textView6.setText("%" + (CountArray[2] * 10) + " Matching");
                textView7.setText(name4);
                textView8.setText("%" + (CountArray[3] * 10) + " Matching");
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(name1);
                textView2.setText("%" + (CountArray[0] * 10) + " Matching");
                textView3.setText(name2);
                textView4.setText("%" + (CountArray[1] * 10) + " Matching");
                textView5.setText(name3);
                textView6.setText("%" + (CountArray[2] * 10) + " Matching");
                textView7.setText(name4);
                textView8.setText("%" + (CountArray[3] * 10) + " Matching");
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
        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", textView1.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", textView3.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", textView5.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", textView7.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
    }

}