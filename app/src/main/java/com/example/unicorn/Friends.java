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

import java.util.HashMap;
import java.util.Map;

public class Friends extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    DocumentReference docRef2;
    CollectionReference Friends;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView15;
    TextView textView16;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    ImageButton homeBtn;
    TextView[] backgrounds;
    TextView[] names;
    Button[] buttons;
    String[] ids;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Friends = firestore.collection("Friends");

        textView1 = findViewById(R.id.background1);
        textView2 = findViewById(R.id.background2);
        textView3 = findViewById(R.id.background3);
        textView4 = findViewById(R.id.background4);
        textView5 = findViewById(R.id.background5);
        textView6 = findViewById(R.id.background6);
        textView7 = findViewById(R.id.background7);
        textView8 = findViewById(R.id.background8);

        textView9 = findViewById(R.id.tt1);
        textView10 = findViewById(R.id.tt2);
        textView11 = findViewById(R.id.tt3);
        textView12 = findViewById(R.id.tt4);
        textView13 = findViewById(R.id.tt5);
        textView14 = findViewById(R.id.tt6);
        textView15 = findViewById(R.id.tt7);
        textView16 = findViewById(R.id.tt8);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);

        homeBtn = findViewById(R.id.imageButton8);

        names = new TextView[8];
        backgrounds = new TextView[8];
        buttons = new Button[8];
        ids = new String[8];

        backgrounds[0] = textView1;
        backgrounds[1] = textView2;
        backgrounds[2] = textView3;
        backgrounds[3] = textView4;
        backgrounds[4] = textView5;
        backgrounds[5] = textView6;
        backgrounds[6] = textView7;
        backgrounds[7] = textView8;

        names[0] = textView9;
        names[1] = textView10;
        names[2] = textView11;
        names[3] = textView12;
        names[4] = textView13;
        names[5] = textView14;
        names[6] = textView15;
        names[7] = textView16;

        buttons[0] = b1;
        buttons[1] = b2;
        buttons[2] = b3;
        buttons[3] = b4;
        buttons[4] = b5;
        buttons[5] = b6;
        buttons[6] = b7;
        buttons[7] = b8;

        firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int i = 0;
                    for (QueryDocumentSnapshot document : task.getResult()){
                        buttons[i].setVisibility(View.VISIBLE);
                        backgrounds[i].setVisibility(View.VISIBLE);
                        names[i].setText(document.getData().get("Name").toString());
                        ids[i] = document.getData().get("ID2").toString();
                        i++;
                    }
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[0]);
                friend.put("Name" , names[0].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[0])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[0].setText("");
                buttons[0].setVisibility(View.INVISIBLE);
                backgrounds[0].setVisibility(View.INVISIBLE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[1]);
                friend.put("Name" , names[1].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[1])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[1].setText("");
                buttons[1].setVisibility(View.INVISIBLE);
                backgrounds[1].setVisibility(View.INVISIBLE);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[2]);
                friend.put("Name" , names[2].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[2])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[2].setText("");
                buttons[2].setVisibility(View.INVISIBLE);
                backgrounds[2].setVisibility(View.INVISIBLE);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[3]);
                friend.put("Name" , names[3].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[3])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[3].setText("");
                buttons[3].setVisibility(View.INVISIBLE);
                backgrounds[3].setVisibility(View.INVISIBLE);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[4]);
                friend.put("Name" , names[4].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[4])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[4].setText("");
                buttons[4].setVisibility(View.INVISIBLE);
                backgrounds[4].setVisibility(View.INVISIBLE);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[5]);
                friend.put("Name" , names[5].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[5])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[5].setText("");
                buttons[5].setVisibility(View.INVISIBLE);
                backgrounds[5].setVisibility(View.INVISIBLE);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[6]);
                friend.put("Name" , names[6].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[6])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[6].setText("");
                buttons[6].setVisibility(View.INVISIBLE);
                backgrounds[6].setVisibility(View.INVISIBLE);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> friend = new HashMap<>();
                friend.put("ID1" , user.getUid());
                friend.put("ID2" , ids[7]);
                friend.put("Name" , names[7].getText().toString());
                Friends.add(friend);
                firestore.collection("FriendRequests").whereEqualTo("ID1", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if((document.getData().get("ID2").toString()).equals(ids[7])){
                                    firestore.collection("FriendRequests").document(document.getId()).delete();
                                }
                            }
                        }
                    }
                });
                names[7].setText("");
                buttons[7].setVisibility(View.INVISIBLE);
                backgrounds[7].setVisibility(View.INVISIBLE);
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
    }
}