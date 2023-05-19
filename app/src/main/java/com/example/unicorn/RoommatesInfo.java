package com.example.unicorn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RoommatesInfo extends AppCompatActivity {
    FirebaseFirestore firestore;
    private final int GALLERY_REC_CODE = 1000;
    String gender;
    String campus;
    String workintheroom;
    String smoke;
    String cook;
    String instrument;
    String sleeplight;
    String roommatecount;
    String sleeptime;
    String getuptime;
    ImageButton profileBtn;
    Button imageBtn;
    Button saveBtn;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    RadioButton rb6;
    RadioButton rb7;
    RadioButton rb8;
    RadioButton rb9;
    RadioButton rb10;
    RadioButton rb11;
    RadioButton rb12;
    RadioButton rb13;
    RadioButton rb14;
    RadioButton rb15;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    EditText editText1;
    EditText editText2;
    ImageView imageview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommates_info);

        firestore = FirebaseFirestore.getInstance();

        imageview = findViewById(R.id.imageView3);

        profileBtn =findViewById(R.id.imageButton4);
        imageBtn = findViewById(R.id.imageBtn);
        saveBtn = findViewById(R.id.button);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rb6 = findViewById(R.id.rb6);
        rb7 = findViewById(R.id.rb7);
        rb8 = findViewById(R.id.rb8);
        rb9 = findViewById(R.id.rb9);
        rb10 = findViewById(R.id.rb10);
        rb11 = findViewById(R.id.rb11);
        rb12 = findViewById(R.id.rb12);
        rb13 = findViewById(R.id.rb13);
        rb14 = findViewById(R.id.rb14);
        rb15 = findViewById(R.id.rb15);

        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        editText1 = findViewById(R.id.editTextText6);
        editText2 = findViewById(R.id.editTextText7);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , Profile.class);
                startActivity(intent);
                finish();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGender();
                setCampus();
                setWorkplace();
                setSmoke();
                setCook();
                setInstrument();
                setSleepLight();
                RoommateCount();
                SleepTime(editText1.getText().toString());
                getUpTime(editText2.getText().toString());

                Map<String, Object> RoommateInfo = new HashMap<>();
                RoommateInfo.put("Gender", gender);
                RoommateInfo.put("Campus", campus);
                RoommateInfo.put("WorkPlace", workintheroom);
                RoommateInfo.put("Smoke", smoke);
                RoommateInfo.put("Cook" , cook);
                RoommateInfo.put("Instrument" , instrument);
                RoommateInfo.put("Sleep Light" , sleeplight);
                RoommateInfo.put("Roommate Count" , roommatecount);
                RoommateInfo.put("Sleep Time" , sleeptime);
                RoommateInfo.put("Get Up Time" , getuptime);

                firestore.collection("RoommateInfos").add(RoommateInfo);
            }
        });
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REC_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REC_CODE){
                imageview.setImageURI(data.getData());
            }
        }
    }

    public void setGender(){
        if(rb1.isChecked()){gender = "Yes";}
        else if(rb2.isChecked()){gender = "No";}
    }

    public void setCampus(){
        if(rb3.isChecked()){campus = "Main";}
        else if(rb4.isChecked()){campus = "East";}
    }

    public void setWorkplace(){
        if(rb5.isChecked()){workintheroom = "Yes";}
        else if(rb6.isChecked()){workintheroom = "No";}
        else if(rb7.isChecked()){workintheroom = "Sometimes";}
    }

    public void setSmoke(){
        if(rb8.isChecked()){smoke = "Yes";}
        else if(rb9.isChecked()){smoke = "No";}
    }

    public void setCook(){
        if(rb10.isChecked()){cook = "Yes";}
        else if(rb11.isChecked()){cook = "No";}
    }

    public void setInstrument(){
        if(rb12.isChecked()){instrument = "Yes";}
        else if(rb13.isChecked()){instrument = "No";}
    }

    public void setSleepLight(){
        if(rb14.isChecked()){sleeplight = "Yes";}
        else if(rb15.isChecked()){sleeplight = "No";}
    }

    public void RoommateCount(){
        if(checkBox1.isChecked()){
            roommatecount = "2";
        }
        else if(checkBox2.isChecked()){
            roommatecount = "3";
        }
        else if(checkBox3.isChecked()){
            roommatecount = "4";
        }
    }

    public void SleepTime(String x){
        sleeptime = x;
    }

    public void getUpTime(String x){
        getuptime = x;
    }

    public void uploadSchedule(){

    }
}