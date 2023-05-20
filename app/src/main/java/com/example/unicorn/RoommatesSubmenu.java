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
    int matchCount;
    ArrayList<Integer> counts;
    ArrayList<String> ids;
    String[] IDArray = new String[4];
    int[] CountArray = new int[4];
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
    TextView[] textViews = new TextView[8];
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

        textView1 = findViewById(R.id.t1);
        textView2 = findViewById(R.id.t2);
        textView3 = findViewById(R.id.t3);
        textView4 = findViewById(R.id.t4);
        textView5 = findViewById(R.id.t5);
        textView6 = findViewById(R.id.t6);
        textView7 = findViewById(R.id.t7);
        textView8 = findViewById(R.id.t8);

        textViews[0] = textView1;
        textViews[1] = textView2;
        textViews[2] = textView3;
        textViews[3] = textView4;
        textViews[4] = textView5;
        textViews[5] = textView6;
        textViews[6] = textView7;
        textViews[7] = textView8;

        ID = user.getUid();

        counts = new ArrayList<>();
        ids = new ArrayList<>();

        getInfos();
        matchAll();
        //setMatches();
        //setTexts();

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

    public void matchAll(){
        firestore.collection("RoommateInfos")
                .whereNotEqualTo("ID", ID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                docRef = firestore.collection("RoommateInfos").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            matchCount = 0;
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

                                            match(gender,gender2);
                                            match(campus,campus2);
                                            match(workintheroom,workintheroom2);
                                            match(smoke,smoke2);
                                            match(cook,cook2);
                                            match(instrument,instrument2);
                                            match(sleeplight,sleeplight2);
                                            match(roommatecount,roommatecount2);
                                            match(sleeplight,sleeplight2);
                                            match(getuptime,getuptime2);

                                            counts.add(matchCount);
                                            ids.add(ID2);
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
    }

    public void match(String item, String item2){
        if(item.equals(item2)){
            matchCount++;
        }
    }

    public void getInfos(){
        firestore.collection("RoommateInfos")
                .whereEqualTo("ID", ID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
    }

    public void setMatches(){
        for(int i = 0 ; i < 4 ; i++){
            int max = counts.get(0);
            int position = 0;
            for(int j = 1 ; j < counts.size() ; j++){
                if(counts.get(j) > max){
                    max = counts.get(j);
                    position = j;
                }
            }
            CountArray[i] = counts.get(position);
            IDArray[i] = ids.get(position);
            counts.set(position,0);
        }
    }

    public void setTexts(){
        for(int i = 0 ; i < 4 ; i= i+2){
            textViews[i].setText(IDArray[0]);
        }
    }
}