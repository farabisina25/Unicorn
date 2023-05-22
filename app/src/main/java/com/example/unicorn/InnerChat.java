package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class InnerChat extends AppCompatActivity {
    ImageButton homepagebutton;
    ImageButton returnTextBtn;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DocumentReference docRef;
    DocumentReference docRef2;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    String messageName;
    String myName;
    CollectionReference Messages;
    Button send;
    EditText editText;
    ImageView image;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_chat);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Messages = firestore.collection("Messages");

        textView1 = findViewById(R.id.message1);
        textView2 = findViewById(R.id.message2);
        textView3 = findViewById(R.id.message3);
        textView4 = findViewById(R.id.message4);
        textView5 = findViewById(R.id.message5);
        textView6 = findViewById(R.id.message6);
        textView7 = findViewById(R.id.NameSurname);
        editText = findViewById(R.id.editmessage);

        homepagebutton = findViewById(R.id.homeBtn);
        returnTextBtn = findViewById(R.id.imageButton9);
        send = findViewById(R.id.sendBtn);

        image = findViewById(R.id.imageView3);

        homepagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        returnTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Chat.class);
                startActivity(intent);
                finish();
            }
        });

        firestore.collection("Profiles").whereEqualTo("ID", user.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        docRef = firestore.collection("Profiles").document(document.getId());
                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    messageName = documentSnapshot.getData().get("ChatProfile").toString();
                                    myName = documentSnapshot.getData().get("NameLastname").toString();
                                    textView7.setText(documentSnapshot.getData().get("ChatProfile").toString());
                                    Map<String, Object> message = new HashMap<>();
                                    message.put(messageName + "to" + myName + 1, textView1.getText().toString());
                                    message.put(messageName + "to" + myName + 2, textView3.getText().toString());
                                    message.put(messageName + "to" + myName + 3, textView5.getText().toString());
                                    message.put(myName + "to" + messageName + 1, textView2.getText().toString());
                                    message.put(myName + "to" + messageName + 2, textView4.getText().toString());
                                    message.put(myName + "to" + messageName + 3, textView6.getText().toString());

                                    Messages.document(user.getUid()).set(message);
                                }
                            }
                        });

                    }
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef = firestore.collection("Profiles").document(user.getUid());
                docRef.update(myName + "to" + messageName + 1, textView3.getText().toString());
                docRef.update(myName + "to" + messageName + 2, textView5.getText().toString());
                docRef.update(myName + "to" + messageName + 3, editText.getText().toString());

                image.setVisibility(View.VISIBLE);
                textView6.setText(editText.getText());
                editText.setText("");
            }
        });

    }
}