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

import java.util.Date;

import Models.Expenses;
import Models.Receipts;

public class TransactionActivity extends AppCompatActivity {

    EditText operation;
    EditText typeOperation;
    EditText categories;
    EditText label;
    Button validate_btn;

    TextView petTransacTV_label;
    TextView petTransacTV_amount;
    TextView petTransacTV_currency;
    TextView petTransacTV_date;
    TextView petTransacTV_category;

    String petType;

    private DatabaseReference mDatabase;
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        petTransacTV_label = findViewById(R.id.petTransac_label_TV);
        petTransacTV_amount = findViewById(R.id.petTransac_amount_TV);
        petTransacTV_currency = findViewById(R.id.petTransac_currency_TV);
        petTransacTV_date = findViewById(R.id.petTransac_date_TV);
        petTransacTV_category = findViewById(R.id.petTransac_category_TV);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_label)) {
            String petLabel = bundle.getString(DashboardActivity.EXTRA_Transaction_label);
            petTransacTV_label.setText(petLabel);
        }
        if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_amount)) {
            String petAmount = bundle.getString(DashboardActivity.EXTRA_Transaction_amount);
            petTransacTV_amount.setText(petAmount);
        }
        if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_currency)) {
            String petCurrency = bundle.getString(DashboardActivity.EXTRA_Transaction_currency);
            petTransacTV_currency.setText(petCurrency);
        }
        /*if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_date)) {
            String petDate = bundle.getString(DashboardActivity.EXTRA_Transaction_date);
            petTransacTV_date.setText(petDate);
        }*/
        if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_category)) {
            String petCategory = bundle.getString(DashboardActivity.EXTRA_Transaction_category);
            petTransacTV_category.setText(petCategory);
        }
        if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_type)) {
            petType = bundle.getString(DashboardActivity.EXTRA_Transaction_type);
        }



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
        validate_btn=findViewById(R.id.buttonAddOperation);
        label=findViewById(R.id.label);
        String s4 = "receipts";
        validate_btn.setOnClickListener(v -> {
            final String[] capital3 = new String[1];
            Log.d("tdst", typeOperation.getText().toString());
            if(petType.equals(s4)){
                Receipts receipts = new Receipts("lebel",categories.getText().toString(),Double.parseDouble(operation.getText().toString()));
                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child(String.valueOf(date)).setValue(receipts);

                Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").get().addOnCompleteListener(task -> {
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
                        Intent intentStep2=new Intent(TransactionActivity.this, DashboardActivity.class);
                        startActivity(intentStep2);

                    }
                });

            }
        });

    }
}