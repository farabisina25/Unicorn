package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
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

public class BookSubmenu extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    CollectionReference Books;
    String BookName;
    ImageButton homeBtn;
    ImageButton profileBtn;
    Button searchBtn;
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
    AutoCompleteTextView actextView;
    String[] books;
    String BookComments;
    String BookAuthor;
    String BookType;
    String BookPrice;
    String OwnerName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_submenu);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Books = firestore.collection("Books");

        homeBtn = findViewById(R.id.imageButton3);
        searchBtn  =findViewById(R.id.button10);
        profileBtn = findViewById(R.id.imageButton5);

        actextView  =findViewById(R.id.autoCompleteTextView2);

        textView1 = findViewById(R.id.textView31);
        textView2 = findViewById(R.id.textView32);
        textView3 = findViewById(R.id.textView33);
        textView4 = findViewById(R.id.textView35);
        textView5 = findViewById(R.id.textView36);
        textView6 = findViewById(R.id.textView37);
        textView7 = findViewById(R.id.textView38);
        textView8 = findViewById(R.id.textView39);
        textView9 = findViewById(R.id.textView40);
        textView10 = findViewById(R.id.textView41);
        textView11 = findViewById(R.id.textView42);

        books = new String[50];

        setBooks();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        actextView.setAdapter(adapter);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ProfilesSubmenu.class);
                startActivity(intent);
                finish();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookName = actextView.getText().toString();
                if(BookName != null){
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);
                    profileBtn.setVisibility(View.VISIBLE);
                    profileBtn.setClickable(true);

                    getBook(BookName);
                }
            }
        });
    }

    public void setBooks(){
        firestore.collection("Books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists() && i < 10){
                                    books[i] = document.getData().get("Name").toString();
                                    i++;
                                }
                            }
                        }
                    }
                });
        for(int i = 0 ; i< 50 ; i++){
            if(books[i] == null){
                books[i] = "!";
            }
        }
    }

    public void getBook(String name){
        firestore.collection("Books")
                .whereEqualTo("Name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                docRef = firestore.collection("Books").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()){
                                            BookType = documentSnapshot.getData().get("Type").toString();
                                            BookName = documentSnapshot.getData().get("Name").toString();
                                            BookAuthor = documentSnapshot.getData().get("Author").toString();
                                            BookPrice = documentSnapshot.getData().get("Price").toString();
                                            BookComments = documentSnapshot.getData().get("Comments").toString();
                                            OwnerName = documentSnapshot.getData().get("OwnerName").toString();

                                            textView9.setText(BookType);
                                            textView6.setText(BookName);
                                            textView7.setText(BookAuthor);
                                            textView8.setText(BookPrice);
                                            textView10.setText(BookComments);
                                            textView11.setText(OwnerName);
                                        }
                                    }
                                });

                            }
                        }
                    }
                });
    }
}