package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {

    TextView petTransacTV_label;
    TextView petTransacTV_amount;
    TextView petTransacTV_currency;
    TextView petTransacTV_date;
    TextView petTransacTV_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
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
    }
}