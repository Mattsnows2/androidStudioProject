package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private DatabaseReference mDatabase;

    Animation topAnim, rotateAnim;
    TextView textTest;
    ImageView logoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        setContentView(R.layout.activity_splash_screen);


        topAnim=AnimationUtils.loadAnimation(this, R.anim.top_animation);
        rotateAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_image);

        textTest=findViewById(R.id.textView3);
        logoImage=findViewById(R.id.imageView);
        textTest.setAnimation(topAnim);

        logoImage.setAnimation(rotateAnim);









        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,HomeConnectedActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }
}