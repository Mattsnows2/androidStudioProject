package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Expenses;
import Models.Receipts;

public class TransactionActivity extends AppCompatActivity {

    EditText operation;
    EditText typeOperation;
    EditText categories;
    TextView label;
    Button button;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){



        }else{
            Log.i("y2","pas connécté");
        }
        super.onCreate(savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
         setContentView(R.layout.activity_transaction);
        operation=findViewById(R.id.editTextOperation);
        typeOperation=findViewById(R.id.editTextTypeOperation);
        categories=findViewById(R.id.categories);
        button=findViewById(R.id.buttonAddOperation);
        label=findViewById(R.id.label);
        String s4 = "receipts";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] capital3 = new String[1];
                Log.d("tdst", typeOperation.getText().toString());
                if(typeOperation.getText().toString().equals(s4)){
                    Receipts receipts = new Receipts("lebel","car",Double.parseDouble(operation.getText().toString()));
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child("operation").setValue(Double.parseDouble(operation.getText().toString()));
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child("operation").child("categorie").setValue((categories.getText().toString()));
                    Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            }
                            else {
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                capital3[0] = String.valueOf(task.getResult().getValue());
                                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").setValue((Double.parseDouble(capital3[0])+Double.parseDouble(operation.getText().toString())));
                                Intent intentStep2=new Intent(TransactionActivity.this, DashboardActivity.class);
                                startActivity(intentStep2);

                            }
                        }
                    });
                    }else{
                    Expenses expenses = new Expenses("label","categories", Double.parseDouble((operation.getText().toString())));
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("expenses").child("operation").setValue(Double.parseDouble(operation.getText().toString()));
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("expenses").child("categories").setValue((categories.getText().toString()));
                    Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            }
                            else {
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                capital3[0] = String.valueOf(task.getResult().getValue());
                                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").setValue((Double.parseDouble(capital3[0])-Double.parseDouble(operation.getText().toString())));
                                Intent intentStep2=new Intent(TransactionActivity.this, DashboardActivity.class);
                                startActivity(intentStep2);

                            }
                        }
                    });

                }
            }
        });

    }
}