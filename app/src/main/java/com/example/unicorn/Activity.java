/**
 This class represents an activity screen that users can create and save activities.
 */
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


    /**
     * Initializes the activity and sets up the layout.
     * Initializes the Firebase Firestore instance.
     * @param savedInstanceState The saved instance state bundle.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        firestore = FirebaseFirestore.getInstance();

        // Assign view elements to variables
        imagebutton2 = findViewById(R.id.profileBtn2);
        buttonCreate = findViewById(R.id.buttonCreate);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText9);
        radioButton17  =findViewById(R.id.radioButton17);
        radioButton18  =findViewById(R.id.radioButton18);
        radioButton19  =findViewById(R.id.radioButton19);

        // Set click listener for the create button
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get input values and set activity properties
                setName(editText5.getText().toString());
                setDate(editText6.getText().toString());
                setPlace(editText7.getText().toString());
                setDescription(editText8.getText().toString());
                setType();

                // Create a map to store the activity data
                Map<String, Object> activity = new HashMap<>();
                activity.put("Name", name);
                activity.put("Date", date);
                activity.put("Place", place);
                activity.put("Description", description);
                activity.put("Type" , type);

                // Add the activity to the Firestore collection
                firestore.collection("Activities").add(activity);

                // Reset input fields and radio buttons
                radioButton17.setChecked(false);
                radioButton18.setChecked(false);
                radioButton19.setChecked(false);
                editText6.setText("");
                editText7.setText("");
                editText8.setText("");
                editText5.setText("");
            }
        });

        // Set click listener for the profile button.
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Open the profile activity
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Sets the name of the activity.
     * @param x The name of the activity.
     */
    public void setName(String x){
        name = x;
    }

    /**
     * Sets the place of the activity.
     * @param x The place of the activity.
     */
    public void setPlace(String x){
        place = x;
    }

    /**
     * Sets the description of the activity.
     * @param x The description of the activity.
     */
    public void setDescription(String x){
        description = x;
    }

    /**
     * Sets the date of the activity.
     * @param x The date of the activity.
     */
    public void setDate(String x){
        date = x;
    }

    /**
     * Sets the type of the activity.
     */
    public void setType(){
        if(radioButton17.isChecked()){
            type = "Concert";
        }
        else if(radioButton18.isChecked()){
            type = "Theatre";
        }
        else{
            type = "Party/Festival";
        }
    }
}