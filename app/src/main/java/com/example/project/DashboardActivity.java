package com.example.project;

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

    RecyclerView capital_list_item;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        receipts_btn = findViewById(R.id.receiptsBtn);
        receipts_btn.setOnClickListener(v -> {
            //DO SOMETHING WHEN CLICKED!
        });
        expenses_btn = findViewById(R.id.expensesBtn);
        expenses_btn.setOnClickListener(v -> {
            //DO SOMETHING WHEN CLICKED!
        });

        capital_list_item = findViewById(R.id.transactionList);
        capital_list_item.hasFixedSize();
        capital_list_item.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("food", 5, "€", R.drawable.rocket));

        transactionAdapter = new TransactionAdapter(transactions);

        transactionAdapter.setOnClickListener(transaction -> Toast.makeText(this, transaction.getLabel(), Toast.LENGTH_SHORT).show());

        capital_list_item.setAdapter(transactionAdapter);
    }
}


