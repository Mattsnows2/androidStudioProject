package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
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
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            "".isEmpty();

        } else {
            Log.i("y2", "pas connecté");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        EditText email = (EditText) findViewById(R.id.editEmailText);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));


        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        Button submit = (Button) findViewById(R.id.submit);
        Button delete = (Button) findViewById(R.id.delete_account);

        email.setText(user.getEmail());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    final String[] message = {new String("Data changed")};
                    if (!email.getText().toString().isEmpty()) {
                        if (email.getText().toString().equals(user.getEmail())) {
                            message[0] += " Current Email is equal to new Email" + "\n";
                        } else {
                            user.updateEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        message[0] += " Current Email is " + email.getText().toString() + "\n";
                                    }
                                }
                            });
                        }
                    }
                    if (!password.getText().toString().isEmpty()) {
                        user.updatePassword(password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    message[0] += " Current password is " + password.getText().toString() + "\n";
                                    password.getText().clear();
                                }
                            }
                        });
                    }
                    Toast.makeText(getApplicationContext(), message[0], Toast.LENGTH_LONG).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder.setMessage(R.string.r_u_sure).setTitle(R.string.confirm_delete)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent intentLogin = new Intent(UserActivity.this, HomeActivity.class);
                                        startActivity(intentLogin);
                                        dialog.cancel();
                                        Toast.makeText(getApplicationContext(), "Account succefully deleted",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Action canceled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}