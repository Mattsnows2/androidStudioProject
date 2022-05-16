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

    TextView petTransacTV_label;
    TextView petTransacTV_amount;
    TextView petTransacTV_currency;
    TextView petTransacTV_date;
    TextView petTransacTV_category;

    TextView petTransacTV;

    private DatabaseReference mDatabase;
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  petTransacTV = findViewById(R.id.petTransacTV);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction)) {
            String petName = bundle.getString(DashboardActivity.EXTRA_Transaction);
            petTransacTV.setText(petName);
        }


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){



        }else{
            Log.i("y2","pas connécté");
        }
        super.onCreate(savedInstanceState);
        petTransacTV_label = findViewById(R.id.petTransac_label_TV);
        petTransacTV_amount = findViewById(R.id.petTransac_amount_TV);
        petTransacTV_currency = findViewById(R.id.petTransac_currency_TV);
        petTransacTV_date = findViewById(R.id.petTransac_date_TV);
        petTransacTV_category = findViewById(R.id.petTransac_category_TV);

      /*  Bundle bundle = getIntent().getExtras();

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
       /* if (bundle != null && bundle.containsKey(DashboardActivity.EXTRA_Transaction_category)) {
            String petCategory = bundle.getString(DashboardActivity.EXTRA_Transaction_category);
            petTransacTV_category.setText(petCategory);
        }*/
    }
}