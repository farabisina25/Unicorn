package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class HomePage extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    DocumentReference docRef2;
    CollectionReference Profiles;
    TextView textView15;
    TextView textView16;
    TextView textView17;
    TextView textView18;
    TextView textView19;
    TextView textView20;
    TextView nameSurname1;
    TextView nameSurname2;
    TextView nameSurname3;
    TextView nameSurname4;
    Object[] textFields;
    Button button7;
    Button button8;
    Button button9;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    ImageButton Profile1;
    ImageButton Profile2;
    ImageButton Profile3;
    ImageButton Profile4;
    Object[] imageButtons;
    ImageButton activity1;
    ImageButton activity2;
    ImageButton activity3;
    ImageButton activity4;
    Object[] activities;

    TextView actText1;
    TextView actText2;
    TextView actText3;
    TextView actText4;
    Object[] acttexts;

    int i;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Profiles = firestore.collection("Profiles");

        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);

        nameSurname1 = findViewById(R.id.nameSurname1);
        nameSurname2 = findViewById(R.id.nameSurname2);
        nameSurname3 = findViewById(R.id.nameSurname3);
        nameSurname4 = findViewById(R.id.nameSurname4);

        textFields = new Object[4];
        textFields[0] = nameSurname1;
        textFields[1] = nameSurname2;
        textFields[2] = nameSurname3;
        textFields[3] = nameSurname4;

        Profile1 = findViewById(R.id.profile1);
        Profile2 = findViewById(R.id.profile2);
        Profile3 = findViewById(R.id.profile3);
        Profile4 = findViewById(R.id.profile4);

        imageButtons = new Object[4];
        imageButtons[0] = Profile1;
        imageButtons[1] = Profile2;
        imageButtons[2] = Profile3;
        imageButtons[3] = Profile4;

        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);

        actText1 = findViewById(R.id.activity1);
        actText2 = findViewById(R.id.activity2);
        actText3 = findViewById(R.id.activity3);
        actText4 = findViewById(R.id.activity4);

        acttexts = new Object[4];
        acttexts[0] = actText1;
        acttexts[1] = actText2;
        acttexts[2] = actText3;
        acttexts[3] = actText4;

        activity1 = findViewById(R.id.imageAct1);
        activity2 = findViewById(R.id.imageAct2);
        activity3 = findViewById(R.id.imageAct3);
        activity4 = findViewById(R.id.imageAct4);

        activities = new Object[4];
        activities[0] = activity1;
        activities[1] = activity2;
        activities[2] = activity3;
        activities[3] = activity4;

        docRef2 = firestore.collection("Profiles").document(user.getUid());
        docRef2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    textView16.setText(documentSnapshot.getData().get("NameLastname").toString());
                }
            }
        });
        set();
        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , EventsSubmenu2.class);
                startActivity(intent);
                finish();
            }
        });
        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , EventsSubmenu2.class);
                startActivity(intent);
                finish();
            }
        });
        activity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , EventsSubmenu2.class);
                startActivity(intent);
                finish();
            }
        });
        activity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , EventsSubmenu2.class);
                startActivity(intent);
                finish();
            }
        });
        Profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", nameSurname1.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        Profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", nameSurname2.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        Profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", nameSurname3.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        Profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update("ShowProfile", nameSurname4.getText().toString());
                Intent intent = new Intent(getApplicationContext() , ShowProfile.class);
                startActivity(intent);
                finish();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , BookSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , RoommatesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Chat.class);
                startActivity(intent);
                finish();
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext() , Login.class);
                startActivity(intent);
                finish();
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , EventsSubmenu2.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void set(){
        firestore.collection("Profiles").whereNotEqualTo("ID", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                docRef = firestore.collection("Profiles").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists() && i < 4){
                                            String name = documentSnapshot.getData().get("NameLastname").toString();
                                            ((TextView)textFields[i]).setText(name);
                                            ((ImageButton)imageButtons[i]).setVisibility(View.VISIBLE);
                                            i++;
                                        }
                                    }
                                });

                            }
                        }
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<QuerySnapshot> task) {
                        firestore.collection("Activities")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            i = 0;
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                if(document.exists() && i < 4){
                                                    String name = document.getData().get("Name").toString();
                                                    ((TextView)acttexts[i]).setText(name);
                                                    ((ImageButton)activities[i]).setVisibility(View.VISIBLE);
                                                    i++;
                                                }
                                            }
                                        }
                                    }
                                });
                    }
                });
    }
}