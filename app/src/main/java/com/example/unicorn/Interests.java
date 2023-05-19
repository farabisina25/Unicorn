package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interests extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference Interests;
    ImageButton profileBtn;
    Button musicsBtn;
    Button sportsBtn;
    Button booksBtn;
    Button clubsBtn;
    public MultiAutoCompleteTextView editText;
    public MultiAutoCompleteTextView editText1;
    public MultiAutoCompleteTextView editText2;
    public MultiAutoCompleteTextView editText3;
    public String mactv1Input;
    public String mactv2Input;
    public String mactv3Input;
    public String mactv4Input;
    String[] musicsInputs;
    String[] sportsInputs;
    String[] booksInputs;
    String[] clubsInputs;

    public String[] musicTypes = new String[]
            {
                    "Rock", "Pop", "RB", "Electronic", "Jazz", "Folk", "Reggae", "Techno", "Funk",
                    "Opera", "Hip hop", "Classical", "Alternative", "Disco", "Indie Rock", "Blues",
                    "Country", "Punk", "Heavy metal"
            };

    public String[] sports = new String[]
            {
                    "Volleyball", "Baseball", "Basketball", "Football", "Cricket", "Golf", "Tennis",
                    "Hockey", "American Football", "Rugby", "Boxing", "Judo", "Karate", "Taekwondo",
                    "Swimming","Diving","Sailing","Water Polo","Canoeing", "Badminton", "Squash", "Table Tennis",
                    "Athletics", "Cycling", "Gymnastics", "Skateboarding", "Surfing"
            };

    public String[] books = new String[]
            {
                    "Action", "Adventure", "Classics", "Comic", "Graphical Novel", "Detective and Mystery",
                    "Fantasy", "Historical Fiction", "Horror", "Romance", "Science Fiction", "Short Stories",
                    "Suspense and Thrillers", "Biographies and Autobiographies", "Cookbooks", "Essays",
                    "History", "Memoir", "Poetry", "Self-Help", "True Crime"
            };

    public String[] clubs = new String[]
            {
                    "ACM Club", "Aikido Society", "American Culture Society", "Animals' Friends Club", "Archaeology Club",
                    "Art Society", "Astronomy Society", "Atelier Bilke-nt Society ", "Audit and Consulting Club", "Aviation Club",
                    "Bilke-nt Gazette Society ", "Bilke-nt Feuerbach Supporters Club", "Blockchain Society ", "Board Games Society",
                    "Botanical Society", "Chemistry Club", "Chess Society", "Cinema Society","Civilization Society", "Classical Guitar Club",
                    "Coffee Club", "TDP", "Formula Club", "Google Student Developers Club","Music Club","Programming Club",
                    "Photography Club", "Young Entrepreneurs Society"
            };
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Interests = firestore.collection("Interests");

        profileBtn = findViewById(R.id.imageButton10);
        musicsBtn = findViewById(R.id.button15);
        sportsBtn = findViewById(R.id.button16);
        booksBtn = findViewById(R.id.button17);
        clubsBtn = findViewById(R.id.button18);

        editText = findViewById(R.id.mactv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicTypes);
        editText.setAdapter(adapter);
        editText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        editText1 = findViewById(R.id.mactv2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sports);
        editText1.setAdapter(adapter1);
        editText1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        editText2 = findViewById(R.id.mactv3);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        editText2.setAdapter(adapter2);
        editText2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        editText3 = findViewById(R.id.mactv4);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clubs);
        editText3.setAdapter(adapter3);
        editText3.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        isInterestCreated();
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });

        musicsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMusics();
            }
        });

        sportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSports();
            }
        });

        booksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBooks();
            }
        });

        clubsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClubs();
            }
        });
    }
    Map<String,Object> interest = new HashMap<>();
    public void setMusics(){
        mactv1Input = editText.getText().toString();
        musicsInputs = mactv1Input.trim().split("\\s*,\\s*");
        interest.put("MusicString", mactv1Input);

        for(int i = 0 ; i < musicsInputs.length; i++){
            interest.put("Music" + i , musicsInputs[i]);
        }

        Interests.document(user.getUid()).set(interest);
    }

    public void setSports(){
       mactv2Input = editText1.getText().toString();
       sportsInputs = mactv2Input.trim().split("\\s*,\\s*");
        interest.put("SportString", mactv2Input);

        for(int i = 0 ; i < sportsInputs.length; i++){
            interest.put("Sport" + i , sportsInputs[i]);
        }

        Interests.document(user.getUid()).set(interest);
    }


    public void setBooks(){
        mactv3Input = editText2.getText().toString();
        booksInputs = mactv3Input.trim().split("\\s*,\\s*");
        interest.put("BookString", mactv3Input);

        for(int i = 0 ; i < booksInputs.length; i++){
            interest.put("Book" + i, booksInputs[i]);
        }

        Interests.document(user.getUid()).set(interest);
    }

    public void setClubs(){
        mactv4Input = editText3.getText().toString();
        clubsInputs = mactv4Input.trim().split("\\s*,\\s*");
        interest.put("ClubString", mactv4Input);

        for(int i = 0 ; i < clubsInputs.length; i++){
            interest.put("Club" + i , clubsInputs[i]);
        }

        Interests.document(user.getUid()).set(interest);
    }

    public void isInterestCreated(){
        docRef = firestore.collection("Interests").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    mactv1Input = documentSnapshot.getData().get("MusicString").toString();
                    mactv2Input = documentSnapshot.getData().get("SportString").toString();
                    mactv3Input = documentSnapshot.getData().get("BookString").toString();
                    mactv4Input = documentSnapshot.getData().get("ClubString").toString();

                    editText.setText(mactv1Input);
                    editText1.setText(mactv2Input);
                    editText2.setText(mactv3Input);
                    editText3.setText(mactv4Input);

                    musicsBtn.setText("Saved");
                    musicsBtn.setFocusable(false);
                    musicsBtn.setClickable(false);
                    sportsBtn.setText("Saved");
                    sportsBtn.setFocusable(false);
                    sportsBtn.setClickable(false);
                    booksBtn.setText("Saved");
                    booksBtn.setFocusable(false);
                    booksBtn.setClickable(false);
                    clubsBtn.setText("Saved");
                    clubsBtn.setFocusable(false);
                    clubsBtn.setClickable(false);
                    editText.setEnabled(false);
                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                    editText3.setEnabled(false);
                }
            }
        });

    }
}