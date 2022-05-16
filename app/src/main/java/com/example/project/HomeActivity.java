package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    public String Tag="inforamtion";
    //private UserViewModel mUserViewModel;
    Button buttonLogin, buttonCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonLogin=findViewById(R.id.buttonLogin);
        buttonCreateAccount=findViewById(R.id.buttonCreateAccount);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentRegister = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

    }
}