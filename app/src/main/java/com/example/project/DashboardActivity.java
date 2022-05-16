package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button receipts_btn;
    Button expenses_btn;

    static final String EXTRA_PET_NAME = "com.example.project.PET_NAME";

    Toolbar toolbar;

    RecyclerView capitalList;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        receipts_btn = findViewById(R.id.receiptsBtn);
        receipts_btn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ReceiptsActivity.class);
            startActivity(intent);
        });
        expenses_btn = findViewById(R.id.expensesBtn);
        expenses_btn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ExpensesActivity.class);
            startActivity(intent);
        });

        capitalList = findViewById(R.id.transactionList);
        capitalList.hasFixedSize();
        capitalList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("food", 5, "€", R.drawable.rocket));
        transactions.add(new Transaction("candy", 6, "€", R.drawable.rocket));
        transactions.add(new Transaction("soda", 7, "€", R.drawable.rocket));
        transactions.add(new Transaction("museum", 8, "€", R.drawable.rocket));
        transactions.add(new Transaction("flight", 9, "€", R.drawable.rocket));

        transactionAdapter = new TransactionAdapter(transactions);

        transactionAdapter.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, TransactionActivity.class);
            /*intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());*/
            startActivity(intent);
        });


        capitalList.setAdapter(transactionAdapter);

        toolbar = findViewById(R.id.toolbar);

        /*toolbar.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        toolbar.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AccountActivity.class);
            startActivity(intent);
        });*/

    }
}


