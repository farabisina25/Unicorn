package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    Button searchBut;
    ImageButton homepagebutton;
    ImageButton profileBut;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    AutoCompleteTextView autoCompleteTextView;
    TextView textView;
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
    String userName;
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

        textView  = findViewById(R.id.tv);
        textView1 = findViewById(R.id.textView50);
        textView2 = findViewById(R.id.textView51);
        textView3 = findViewById(R.id.textView52);
        textView4 = findViewById(R.id.textView53);
        textView5 = findViewById(R.id.textView54);
        textView6 = findViewById(R.id.textView55);
        textView7 = findViewById(R.id.textView56);
        textView8 = findViewById(R.id.textView57);
        textView9 = findViewById(R.id.textView58);

        autoCompleteTextView = findViewById(R.id.act);

        homepagebutton = findViewById(R.id.imageButton2);
        profileBut = findViewById(R.id.imageButton7);
        searchBut = findViewById(R.id.searchBut);

        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton3);
        rb3 = findViewById(R.id.radioButton4);



        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventtype = "Concert";

                textView.setVisibility(View.VISIBLE);
                autoCompleteTextView.setVisibility(View.VISIBLE);
                searchBut.setVisibility(View.VISIBLE);
                searchBut.setClickable(true);

                setActivities(eventtype);
            }
        });

        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventtype = "Theatre";

                textView.setVisibility(View.VISIBLE);
                autoCompleteTextView.setVisibility(View.VISIBLE);
                searchBut.setVisibility(View.VISIBLE);
                searchBut.setClickable(true);

                setActivities(eventtype);
            }
        });

        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventtype = "Party/Festival";

                textView.setVisibility(View.VISIBLE);
                autoCompleteTextView.setVisibility(View.VISIBLE);
                searchBut.setVisibility(View.VISIBLE);
                searchBut.setClickable(true);

                setActivities(eventtype);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);
        autoCompleteTextView.setAdapter(adapter);

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

        searchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = autoCompleteTextView.getText().toString();
                if(Name != null){
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);
                    profileBut.setVisibility(View.VISIBLE);
                    profileBut.setClickable(true);

                    getActivities(Name);
                }
            }
        });

    }

    public void setActivities(String type){
        firestore.collection("Activities").whereEqualTo("Type" , type)
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

    public void getActivities(String name){
        firestore.collection("Activities")
                .whereEqualTo("Name", name)
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
                                            Name = documentSnapshot.getData().get("Name").toString();
                                            Date = documentSnapshot.getData().get("Date").toString();
                                            Place = documentSnapshot.getData().get("Place").toString();
                                            Description = documentSnapshot.getData().get("Description").toString();

                                            textView5.setText(Name);
                                            textView6.setText(Date);
                                            textView7.setText(Place);
                                            textView8.setText(Description);
                                            setName();
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
    }

    public void setName(){
        firestore.collection("Profiles")
                .whereEqualTo("ID", user.getUid())
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
                                            userName = documentSnapshot.getData().get("NameLastname").toString();

                                            textView9.setText(userName);
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
    }
}