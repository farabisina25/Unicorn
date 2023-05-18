package com.example.unicorn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity extends AppCompatActivity {

    //Comment
    FirebaseFirestore firestore;
    private String name;
    private String place;
    private String date;
    private String description;

    private ImageButton imagebutton2;
    private Button buttonCreate;
    private EditText editText5;
    private EditText editText6;
    private EditText editText7;
    private EditText editText8;
    private RadioButton radioButton17;
    private RadioButton radioButton18;
    private RadioButton radioButton19;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        firestore = FirebaseFirestore.getInstance();

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setName(editText5.getText().toString());
                setDate(editText6.getText().toString());
                setPlace(editText7.getText().toString());
                setDescription(editText8.getText().toString());

                Map<String,Object> profile = new HashMap<>();
                profile.put("Name" , name);
                profile.put("Date" , date);
                profile.put("Place" , place);
                profile.put("Description" , description);

                firestore.collection("Activities").add(profile);
            }
        });
    }

    public void setType(){

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
}