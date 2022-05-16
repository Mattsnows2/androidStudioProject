package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    EditText email, password;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    //private UserViewModel mUserViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton=findViewById(R.id.buttonRegisterPage);
        email=findViewById(R.id.fieldEmail);
        password=findViewById(R.id.fieldPassword);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("message");
        mAuth=FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(email.getText().toString().replaceAll("\\s", ""), password.getText().toString());
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser= mAuth.getCurrentUser();
        if(currentUser!=null){
            reload();
        }
    }


    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("yo","createWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();



                            updateUI(user);

                            Intent intentStep1 = new Intent(RegisterActivity.this, Step1Activity.class);
                            startActivity(intentStep1);





                        }else{
                            Log.w("yo", "createWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this,"Authentication failed", Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }



    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
}


