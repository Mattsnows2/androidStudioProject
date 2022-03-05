package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation bottonAnim, topAnim;
    TextView textTest;
    ImageView logoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        bottonAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        topAnim=AnimationUtils.loadAnimation(this, R.anim.top_animation);

        textTest=findViewById(R.id.textView3);
        logoImage=findViewById(R.id.imageView);
        textTest.setAnimation(topAnim);
        logoImage.setAnimation(bottonAnim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }
}