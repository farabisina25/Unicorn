package com.example.unicorn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class BookInfo extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private final int GALLERY_REC_CODE = 1000;
    String ownerName;
    String name;
    String author;
    String price;
    String comments;
    String type;
    ImageView imageview;
    RadioButton radioButton17;
    RadioButton radioButton18;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    EditText editText9;
    EditText editText10;
    ImageButton profileButton;
    Button createButton;
    Button deleteButton;
    AutoCompleteTextView actw;
    String[] books;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        imageview = findViewById(R.id.imageView5);
        radioButton17 = findViewById(R.id.radioButton17);
        radioButton18 = findViewById(R.id.radioButton18);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);
        actw = findViewById(R.id.act3);
        profileButton = findViewById(R.id.profileBtn);
        createButton = findViewById(R.id.createBtn);
        deleteButton = findViewById(R.id.deleteBtn);
        books = new String[50];

        setBooks();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        actw.setAdapter(adapter);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actw.getText().toString().equals("")){
                    //Do Nothing
                }
                else{
                    String name = actw.getText().toString();
                    firestore.collection("Books")
                            .whereEqualTo("Name" , name)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if(document.exists()){
                                                firestore.collection("Books").document(document.getId()).delete();
                                            }
                                        }
                                    }
                                }
                            });
                }
                actw.setText("");
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOwnerName(editText6.getText().toString());
                setName(editText7.getText().toString());
                setAuthor(editText8.getText().toString());
                setPrice(editText9.getText().toString());
                setComments(editText10.getText().toString());
                if(radioButton17.isChecked()){type = "Reading Book";}
                else if(radioButton18.isChecked()){type = "Lecture Book";}

                Map<String,Object> book = new HashMap<>();
                book.put("Type" , type);
                book.put("OwnerName" , ownerName);
                book.put("Name" , name);
                book.put("Author" , author);
                book.put("Price" , price);
                book.put("Comments" , comments);
                book.put("ID" , user.getUid());

                firestore.collection("Books").add(book);

                radioButton17.setChecked(false);
                radioButton18.setChecked(false);
                editText6.setText("");
                editText7.setText("");
                editText8.setText("");
                editText9.setText("");
                editText10.setText("");
            }
        });
    }
    public void setOwnerName(String x){
        ownerName = x;
    }

    public void setName(String x){
        name = x;
    }

    public void setAuthor(String x){
        author = x;
    }

    public void setPrice(String x){
        price = x;
    }

    public void setComments(String x){
        comments = x;
    }

    public void setBooks(){
        firestore.collection("Books")
                .whereEqualTo("ID" , user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i= 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
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
}