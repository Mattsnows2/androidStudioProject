package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.User;
import ViewModel.UserViewModel;
import dao.UserDAO;
import repository.UserRepository;
import androidx.lifecycle.ViewModelProvider;


public class Step1Activity extends AppCompatActivity {

    Button nextStep;
    LinearProgressIndicator mProgressBar;
    TextInputEditText textCapital;
    UserRepository mUserRepository;
    UserViewModel mUserViewModel;
    private int i =0;
    private Handler hdlr = new Handler();
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){



        }else{
            Log.i("y2","pas connécté");
        }
        super.onCreate(savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        setContentView(R.layout.activity_step1);

        nextStep=findViewById(R.id.buttonNext);
        mProgressBar=findViewById(R.id.progressBarStep1);
        textCapital=findViewById(R.id.capital);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        User newUser = new User(userId,email, 0, 0, 0d);
        String userId2 =  String.valueOf(userId);
        mDatabase.child("users").child(userId2).setValue(newUser);
        mUserViewModel.insert(new User(userId, email, 4,4,0d));



        mUserViewModel.insert(new User(userId, email, 0,0,0d));


        nextStep.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


              i=mProgressBar.getProgress();
              new Thread(new Runnable() {
                  @Override
                  public void run() {
                      while(i<50){
                          i +=1;
                          hdlr.post(new Runnable() {
                              public void run() {
                                  mProgressBar.setProgress(i);
                                  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").setValue(Float.parseFloat(textCapital.getText().toString()));


                              }
                          });

                          try{
                              Thread.sleep(50);

                          }catch (InterruptedException e){
                              e.printStackTrace();
                          }

                      }
                      Intent intentStep2=new Intent(Step1Activity.this, HomeConnectedActivity.class);
                      startActivity(intentStep2);
                  }
              }).start();


            }

        });

    }


}