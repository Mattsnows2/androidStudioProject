package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                Log.d("tdst", typeOperation.getText().toString());
                if(typeOperation.getText().toString().equals(s4)){
                    Receipts receipts = new Receipts("lebel","car",Double.parseDouble(operation.getText().toString()));
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child("operation").setValue(Double.parseDouble(operation.getText().toString()));
                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child("categories").setValue((categories.getText().toString()));
                }else{
                    Log.d("pas recipts","pas receipts");
                }
            }
        });

    }
}