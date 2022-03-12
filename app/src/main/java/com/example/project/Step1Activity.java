package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Step1Activity extends AppCompatActivity {

    Button nextStep;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        nextStep=findViewById(R.id.buttonNext);
        mProgressBar=findViewById(R.id.progressBar3);

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNextStep = new Intent(Step1Activity.this, Step2Activity.class);
                startActivity(intentNextStep);
            }
        });

    }
}