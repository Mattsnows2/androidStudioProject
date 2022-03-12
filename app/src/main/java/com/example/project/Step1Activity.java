package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class Step1Activity extends AppCompatActivity {

    Button nextStep;
    LinearProgressIndicator mProgressBar;
    private int i =0;
    private Handler hdlr = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        nextStep=findViewById(R.id.buttonNext);
        mProgressBar=findViewById(R.id.progressBarStep1);

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

                              }
                          });

                          try{
                              Thread.sleep(50);

                          }catch (InterruptedException e){
                              e.printStackTrace();
                          }

                      }
                      Intent intentStep2=new Intent(Step1Activity.this, Step2Activity.class);
                      startActivity(intentStep2);
                  }
              }).start();


            }

        });

    }
}