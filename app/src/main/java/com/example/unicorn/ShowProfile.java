package com.example.unicorn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
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

import java.util.HashMap;
import java.util.Map;

public class ShowProfile extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference Friends;
    String Description;
    String NameSurname;
    String BirthDate;
    String Department;
    String ProfileShow;
    String ID;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    ImageButton homeBtn;
    Button addfriend;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Friends = firestore.collection("Friends");

        textView1 = findViewById(R.id.textView61);
        textView2 = findViewById(R.id.textView63);
        textView3 = findViewById(R.id.textView65);
        textView4 = findViewById(R.id.textView67);
        textView5 = findViewById(R.id.textView68);

        homeBtn = findViewById(R.id.imageButton11);
        addfriend = findViewById(R.id.addfriend);

        docRef = firestore.collection("Profiles").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    ProfileShow = documentSnapshot.getData().get("ShowProfile").toString();
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                firestore.collection("Profiles").whereEqualTo("NameLastname", ProfileShow).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                docRef = firestore.collection("Profiles").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            NameSurname = documentSnapshot.getData().get("NameLastname").toString();
                                            BirthDate = documentSnapshot.getData().get("Birth").toString();
                                            Department = documentSnapshot.getData().get("Department").toString();
                                            Description = documentSnapshot.getData().get("Description").toString();

                                            textView1.setText(NameSurname);
                                            textView2.setText(BirthDate);
                                            textView3.setText(Department);
                                            textView4.setText(Description);
                                            textView5.setText("Send Message to " + NameSurname);
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addfriend.getText().equals("Add Friend")){
                    addfriend.setText("Waiting...");
                    addfriend.setClickable(false);
                    firestore.collection("Profiles").whereEqualTo("NameLastname", NameSurname).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    docRef = firestore.collection("Profiles").document(document.getId());
                                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if(documentSnapshot.exists()){
                                                ID = documentSnapshot.getData().get("ID").toString();
                                            }
                                        }
                                    });

                                }
                            }
                        }
                    }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            Map<String,Object> friendrequest = new HashMap<>();
                            friendrequest.put("ID" , user.getUid());
                            Friends.document(ID).set(friendrequest);
                        }
                    });
                }
            }
        });
    }
    public void isFriend(){

    }
}