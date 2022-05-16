package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button receipts_btn;
    Button expenses_btn;

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

        transactionAdapter.setOnClickListener(transaction -> Toast.makeText(this, transaction.getLabel(), Toast.LENGTH_SHORT).show());

        capitalList.setAdapter(transactionAdapter);
    }
}


