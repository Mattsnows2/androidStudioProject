package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class FirstSplashScreen extends AppCompatActivity {

    Animation topAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_splash_screen);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        image=findViewById(R.id.logo);

        image.setAnimation(topAnim);

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(FirstSplashScreen.this, DashboardActivity.class);
            startActivity(intent);

            finish();
        },3000);
    }
}