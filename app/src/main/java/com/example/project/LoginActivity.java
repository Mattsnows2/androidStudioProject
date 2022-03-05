package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button loginButtonOnPageLogin;
    EditText username;
    EditText password;
    ProgressBar spinner;
    TextView errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButtonOnPageLogin=findViewById(R.id.buttonLoginPage);
        username=findViewById(R.id.editTextUsername);
        password=findViewById(R.id.editTextPassword);
        spinner=findViewById(R.id.progressBar);
        errorMessage=findViewById(R.id.errorMessage);

        spinner.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);

        loginButtonOnPageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equals("username") && password.getText().toString().equals("1234")){
                    spinner.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(LoginActivity.this, SplashScreen.class);
                            startActivity(intent);
                        }
                    },2000);

                }else{
                    errorMessage.setVisibility(View.VISIBLE);
                }





            }
        });
    }
}