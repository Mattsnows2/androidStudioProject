package com.example.project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    RecyclerView capital_list_item;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        capital_list_item = findViewById(R.id.expensesList);
        capital_list_item.hasFixedSize();
        capital_list_item.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> expenses = new ArrayList<>();
        expenses.add(new Transaction("KFC", 60, "€", R.drawable.email));

        transactionAdapter = new TransactionAdapter(expenses);

        transactionAdapter.setOnClickListener(transaction -> Toast.makeText(this, transaction.getLabel(), Toast.LENGTH_SHORT).show());

        capital_list_item.setAdapter(transactionAdapter);
    }
}
