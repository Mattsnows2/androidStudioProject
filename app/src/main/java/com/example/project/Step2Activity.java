package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class Step2Activity extends AppCompatActivity {

    LinearProgressIndicator mProgressBar;
    Button finishStep;
    private Handler hdlr = new Handler();
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        mProgressBar=findViewById(R.id.progressBarStep2);
        finishStep=findViewById(R.id.buttonFinishStep);

        finishStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=mProgressBar.getProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(i<100){
                            i+=1;

                            hdlr.post(new Runnable() {
                                @Override
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
                    }
                }).start();
            }
        });


    }
}