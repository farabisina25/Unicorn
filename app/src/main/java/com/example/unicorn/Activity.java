package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity extends AppCompatActivity {

    FirebaseFirestore firestore;
    String name;
    String place;
    String date;
    String description;
    String type;

    ImageButton imagebutton2;
    Button buttonCreate;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    RadioButton radioButton17;
    RadioButton radioButton18;
    RadioButton radioButton19;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        firestore = FirebaseFirestore.getInstance();

        imagebutton2 = findViewById(R.id.profileBtn2);
        buttonCreate = findViewById(R.id.buttonCreate);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText9);
        radioButton17  =findViewById(R.id.radioButton17);
        radioButton18  =findViewById(R.id.radioButton18);
        radioButton19  =findViewById(R.id.radioButton19);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setName(editText5.getText().toString());
                setDate(editText6.getText().toString());
                setPlace(editText7.getText().toString());
                setDescription(editText8.getText().toString());
                setType();

                Map<String, Object> activity = new HashMap<>();
                activity.put("Name", name);
                activity.put("Date", date);
                activity.put("Place", place);
                activity.put("Description", description);
                activity.put("Type" , type);

                firestore.collection("Activities1").add(activity);
            }
        });

        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void setName(String x){
        name = x;
    }

    public void setPlace(String x){
        place = x;
    }

    public void setDescription(String x){
        description = x;
    }

    public void setDate(String x){
        date = x;
    }

    public void setType(){
        String x = "";
        if(radioButton17.callOnClick()){
            x = "Concert";
        }
        else if(radioButton18.callOnClick()){
            x = "Theatre";
        }
        else{
            x = "Party/Festival";
        }
        type  = x;
    }
}