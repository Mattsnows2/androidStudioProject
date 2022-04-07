package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button loginButtonOnPageLogin;
    TextInputEditText username;
    TextInputEditText password;
    ProgressBar spinner;
    TextView errorMessage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        loginButtonOnPageLogin=findViewById(R.id.buttonLoginPage);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        spinner=findViewById(R.id.progressBar);
        errorMessage=findViewById(R.id.errorMessage);

        spinner.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);



        loginButtonOnPageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            /*    if( username.getText().toString().equals("user") &&  password.getText().toString().equals("1234")){
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
                }*/

                signIn(username.getText().toString(), password.getText().toString());



            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            spinner.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(LoginActivity.this, SplashScreen.class);
                                    startActivity(intent);
                                }
                            },2000);

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            errorMessage.setVisibility(View.VISIBLE);
                            updateUI(null);
                        }
                    }
                });

    }

        private void reload() { }

        private void updateUI(FirebaseUser user) {

        }
    }
