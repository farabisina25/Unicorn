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

import java.util.ArrayList;

public class Interests extends AppCompatActivity {

    ImageButton profileBtn;
    ImageButton musicBtn;
    ImageButton sportsBtn;
    ImageButton booksBtn;
    ImageButton clubBtn;
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
    public MultiAutoCompleteTextView editText;
    public MultiAutoCompleteTextView editText1;
    public MultiAutoCompleteTextView editText2;
    public MultiAutoCompleteTextView editText3;
    public String mactv1Input;
    public String mactv2Input;
    public String mactv3Input;
    public String mactv4Input;

    public ArrayList<String> mactv1Array = new ArrayList<>();
    public ArrayList<String> mactv2Array = new ArrayList<>();
    public ArrayList<String> mactv3Array = new ArrayList<>();
    public ArrayList<String> mactv4Array = new ArrayList<>();





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);


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



        profileBtn = findViewById(R.id.imageButton10);


        profileBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });

    }







    public void setMusics(View v){
        mactv1Input = editText.getText().toString();
       // mactv1Array = mactv1Input.split("");

    }

    public void setSports(View v){
       mactv2Input = editText1.getText().toString();
       // mactv2Array = mactv2Input.split(" ");
    }


    public void setBooks(View v){
        mactv3Input = editText2.getText().toString();
        // mactv3Array = mactv2Input.split("");

    }

    public void schoolClubs(View v){
        mactv4Input = editText3.getText().toString();
        // mactv4Array = mactv3Input.split("");

    }
}