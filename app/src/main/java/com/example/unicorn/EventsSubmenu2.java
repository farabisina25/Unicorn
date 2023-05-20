package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
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

public class EventsSubmenu2 extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference Books;
    String eventtype;
    ImageButton homepagebutton;
    ImageButton profileBut;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    String Name;
    String Date;
    String Place;
    String Description;
    String[] activities = new String[50];
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_submenu2);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Books = firestore.collection("Activities");

        textView1 = findViewById(R.id.textView50);
        textView2 = findViewById(R.id.textView51);
        textView3 = findViewById(R.id.textView52);
        textView4 = findViewById(R.id.textView53);
        textView5 = findViewById(R.id.textView54);
        textView6 = findViewById(R.id.textView55);
        textView7 = findViewById(R.id.textView56);
        textView8 = findViewById(R.id.textView57);
        textView9 = findViewById(R.id.textView58);

        homepagebutton = findViewById(R.id.imageButton2);
        profileBut = findViewById(R.id.imageButton7);

        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton3);
        rb3 = findViewById(R.id.radioButton4);

        setActivities();

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventtype = "Concert";

                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                profileBut.setVisibility(View.VISIBLE);
                profileBut.setClickable(true);

                getActivities(eventtype);
            }
        });

        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventtype = "Theatre";

                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                profileBut.setVisibility(View.VISIBLE);
                profileBut.setClickable(true);

                getActivities(eventtype);
            }
        });

        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventtype = "Party/Festival";

                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                profileBut.setVisibility(View.VISIBLE);
                profileBut.setClickable(true);

                getActivities(eventtype);
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

        profileBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void setActivities(){
        firestore.collection("Activities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    activities[i] = document.getData().get("Name").toString();
                                    i++;
                                }
                            }
                        }
                    }
                });
        for(int i = 0 ; i< 50 ; i++){
            if(activities[i] == null){
                activities[i] = "!";
            }
        }
    }

    public void getActivities(String type){
        firestore.collection("Activities")
                .whereEqualTo("Name", type)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                docRef = firestore.collection("Activities").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            Name = documentSnapshot.getData().get("Type").toString();
                                            Date = documentSnapshot.getData().get("Name").toString();
                                            Place = documentSnapshot.getData().get("Author").toString();
                                            Description = documentSnapshot.getData().get("Price").toString();

                                            /*textView6.setText(BookName);
                                            textView7.setText(BookAuthor);
                                            textView8.setText(BookPrice);
                                            textView9.setText(BookType);
                                            textView10.setText(BookComments);
                                            textView11.setText(OwnerName);*/
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
    }

}