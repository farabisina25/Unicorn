package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class RoommatesSubmenu extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    int[] counts;
    String[] ids;
    String[] IDArray;
    int[] CountArray;
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
    TextView[] textViews;
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
    String ID2;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommates_submenu);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

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

        textViews = new TextView[8];
        textViews[0] = textView1;
        textViews[1] = textView2;
        textViews[2] = textView3;
        textViews[3] = textView4;
        textViews[4] = textView5;
        textViews[5] = textView6;
        textViews[6] = textView7;
        textViews[7] = textView8;

        for(int i = 0 ; i < counts.length ; i++){
            counts[i] = 0;
        }
        firestore.collection("RoommateInfos").whereEqualTo("ID", ID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
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
        });
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
                                if(documentSnapshot.exists()){
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
                                    ID2 = documentSnapshot.getData().get("ID").toString();
                                    if(gender.equals(gender2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(campus.equals(campus2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(workintheroom.equals(workintheroom2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(smoke.equals(smoke2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(cook.equals(cook2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(instrument.equals(instrument2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(sleeplight.equals(sleeplight2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(roommatecount.equals(roommatecount2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(sleeptime.equals(sleeptime2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    if(getuptime.equals(getuptime2)){
                                        int temp = counts[i];
                                        counts[i] = temp + 1;
                                    }
                                    ids[i] = ID2;
                                    i++;
                                }
                            }
                        });

                    }
                }
            }
        });
        /*for(int i = 0 ; i < 4 ; i++){
            int max = 0;
            int position = 0;
            for(int j = 0 ; j < counts.length ; j++){
                if(counts[j] > max){
                    max = counts[j];
                    position = j;
                }
            }
            CountArray[i] = counts[position];
            IDArray[i] = ids[position];
            counts[position] = 0;
        }
        textView1.setText(IDArray[0]);
        textView2.setText(String.valueOf(CountArray[0]));
        textView3.setText(IDArray[1]);
        textView4.setText(String.valueOf(CountArray[1]));
        textView5.setText(IDArray[2]);
        textView6.setText(String.valueOf(CountArray[2]));
        textView7.setText(IDArray[3]);
        textView8.setText(String.valueOf(CountArray[3]));*/

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
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
        profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getInfo(){

    }
}