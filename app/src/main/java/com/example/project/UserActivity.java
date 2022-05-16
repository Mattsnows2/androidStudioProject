package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {
    //UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){



        }else{
            Log.i("y2","pas connecté");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        EditText email = (EditText) findViewById(R.id.editEmailText);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    user.updateEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Email Changed !" +"\n"+ " Current Email is " + email.getText().toString(), Toast.LENGTH_LONG).show();
                                email.getText().clear();
                            }
                        }
                    });

                }
            }
        });
    }
}