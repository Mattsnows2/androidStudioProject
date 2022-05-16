package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import Models.Expenses;
import Models.Receipts;

public class SetTransactionActivity extends AppCompatActivity {

    EditText operation;
    EditText typeOperation;
    EditText categories;
    EditText label;
    Button validate_btn;



    private DatabaseReference mDatabase;

    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();

      /*  if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_type)) {
            petType = bundle.getString(DashboardActivity.EXTRA_Transaction_type);
        }*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){

        }else{
            Log.i("y2","pas connécté");
        }
        super.onCreate(savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        setContentView(R.layout.activity_set_transaction);
        operation=findViewById(R.id.editTextOperation);
        categories=findViewById(R.id.categories);
        typeOperation=findViewById(R.id.editTextTypeOperation);
        validate_btn=findViewById(R.id.buttonAddOperation);
        label=findViewById(R.id.label);
        String s4 = "receipts";
        validate_btn.setOnClickListener(v -> {
            final String[] capital3 = new String[1];
            Log.d("tdst", typeOperation.getText().toString());
            if(typeOperation.getText().toString().equals(s4)){
                Receipts receipts = new Receipts(label.getText().toString(),categories.getText().toString(),Double.parseDouble(operation.getText().toString()));
                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child(String.valueOf(date)).setValue(receipts);

                Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        capital3[0] = String.valueOf(task.getResult().getValue());
                        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").setValue((Double.parseDouble(capital3[0])+Double.parseDouble(operation.getText().toString())));
                        Intent intentStep2=new Intent(SetTransactionActivity.this, DashboardActivity.class);
                        startActivity(intentStep2);
                    }
                });
            }else{
                Expenses expenses = new Expenses("label",categories.getText().toString(), Double.parseDouble((operation.getText().toString())));
                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("expenses").child(String.valueOf(date)).setValue(expenses);

                Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        capital3[0] = String.valueOf(task.getResult().getValue());
                        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").setValue((Double.parseDouble(capital3[0])-Double.parseDouble(operation.getText().toString())));
                        Intent intentStep2=new Intent(SetTransactionActivity.this, DashboardActivity.class);
                        startActivity(intentStep2);

                    }
                });

            }
        });

    }
}