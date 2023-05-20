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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RoommatesInfo extends AppCompatActivity {
    FirebaseFirestore firestore;
    FirebaseUser user;
    FirebaseAuth mAuth;
    CollectionReference RoommateInfos;
    DocumentReference docRef;
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
    String Email;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommates_info);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        RoommateInfos = firestore.collection("RoommateInfos");

        imageview = findViewById(R.id.imageView3);

        profileBtn =findViewById(R.id.imageButton4);
        imageBtn = findViewById(R.id.imageBtn);
        saveBtn = findViewById(R.id.button);

        Email = user.getEmail();

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

        isRoommateCreated();

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
                /*if(saveBtn.getText().toString().equals("Edit")){
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

                    docRef = firestore.collection("RoommateInfos").document(user.getUid());
                    docRef.update("Gender", gender);
                    docRef.update("Campus", campus);
                    docRef.update("WorkPlace", workintheroom);
                    docRef.update("Smoke", smoke);
                    docRef.update("Cook" , cook);
                    docRef.update("Instrument" , instrument);
                    docRef.update("Sleep Light" , sleeplight);
                    docRef.update("Roommate Count" , roommatecount);
                    docRef.update("Sleep Time" , sleeptime);
                    docRef.update("Get Up Time" , getuptime);
                }*/
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
                RoommateInfo.put("ID" , user.getUid());
                
                RoommateInfos.document(user.getUid()).set(RoommateInfo);
                isRoommateCreated();
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

    public void isRoommateCreated(){
        docRef = firestore.collection("RoommateInfos").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    gender = documentSnapshot.getData().get("Gender").toString();
                    campus = documentSnapshot.getData().get("Campus").toString();
                    workintheroom = documentSnapshot.getData().get("WorkPlace").toString();
                    smoke = documentSnapshot.getData().get("Smoke").toString();
                    cook = documentSnapshot.getData().get("Cook").toString();
                    instrument = documentSnapshot.getData().get("Instrument").toString();
                    sleeplight = documentSnapshot.getData().get("Sleep Light").toString();
                    roommatecount = documentSnapshot.getData().get("Roommate Count").toString();
                    sleeptime = documentSnapshot.getData().get("Sleep Time").toString();
                    getuptime = documentSnapshot.getData().get("Get Up Time").toString();

                    saveBtn.setText("Saved");
                    saveBtn.setClickable(false);
                    saveBtn.setFocusable(false);

                    if(gender.equals("Man")){rb1.setChecked(true);}
                    else if(gender.equals("Woman")){rb2.setChecked(true);}

                    if(campus.equals("Main")){rb3.setChecked(true);}
                    else if(campus.equals("East")){rb4.setChecked(true);}

                    if(workintheroom.equals("Yes")){rb5.setChecked(true);}
                    else if(workintheroom.equals("No")){rb6.setChecked(true);}
                    else if(workintheroom.equals("Sometimes")){rb7.setChecked(true);}

                    if(smoke.equals("Yes")){rb8.setChecked(true);}
                    else if(smoke.equals("No")){rb9.setChecked(true);}

                    if(cook.equals("Yes")){rb10.setChecked(true);}
                    else if(cook.equals("No")){rb11.setChecked(true);}

                    if(instrument.equals("Yes")){rb12.setChecked(true);}
                    else if(instrument.equals("No")){rb13.setChecked(true);}

                    if(sleeplight.equals("Yes")){rb14.setChecked(true);}
                    else if(sleeplight.equals("No")){rb15.setChecked(true);}

                    if(roommatecount.equals("2")){checkBox1.setChecked(true);}
                    else if(roommatecount.equals("3")){checkBox2.setChecked(true);}
                    else if(roommatecount.equals("4")){checkBox3.setChecked(true);}

                    editText1.setText(sleeptime);
                    editText2.setText(getuptime);

                    rb1.setClickable(false);
                    rb2.setClickable(false);
                    rb3.setClickable(false);
                    rb4.setClickable(false);
                    rb5.setClickable(false);
                    rb6.setClickable(false);
                    rb7.setClickable(false);
                    rb8.setClickable(false);
                    rb9.setClickable(false);
                    rb10.setClickable(false);
                    rb11.setClickable(false);
                    rb12.setClickable(false);
                    rb13.setClickable(false);
                    rb14.setClickable(false);
                    rb15.setClickable(false);
                    checkBox1.setClickable(false);
                    checkBox2.setClickable(false);
                    checkBox3.setClickable(false);
                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                }
            }
        });

    }

    public void setGender(){
        if(rb1.isChecked()){gender = "Man";}
        else if(rb2.isChecked()){gender = "Woman";}
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