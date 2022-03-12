package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText confirmPassword;
    boolean validate;
    Button register;
    TextView errorUsername;
    TextView errorPassword;
    TextView errorConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.setUsername);
        password=findViewById(R.id.setPassword);
        confirmPassword=findViewById(R.id.setConfirmPassword);
        register = findViewById(R.id.buttonRegister);
        errorUsername=findViewById(R.id.errorUsername);
        errorPassword=findViewById(R.id.errorPassword);
        errorConfirmPassword=findViewById(R.id.errorConfirmPassword);

        errorUsername.setVisibility(View.INVISIBLE);
        errorPassword.setVisibility(View.INVISIBLE);
        errorConfirmPassword.setVisibility(View.INVISIBLE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
                if(validate==true){
                    Log.i("validation","c'est tout bon");

                }else{
                    Log.i("validation","c'est pas bon");
                }
            }
        });
    }

    public boolean validateForm(String username, String password, String confirmPassword){


        if(username.equals("") && username.length()<6){
            errorUsername.setVisibility(View.VISIBLE);
            validate = false;
        }
        if(password.length()<4){
            errorPassword.setVisibility(View.VISIBLE);
            validate=false;
        }

        if(!confirmPassword.equals(password)){
            errorConfirmPassword.setVisibility(View.VISIBLE);
            return validate=false;
        }
        return validate=true;
    }

}