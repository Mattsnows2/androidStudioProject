package com.example.project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    RecyclerView expensesList;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expensesList = findViewById(R.id.expenses_list);
        expensesList.hasFixedSize();
        expensesList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> expenses = new ArrayList<>();
        expenses.add(new Transaction("KFC", 60, "€", R.drawable.email));

        transactionAdapter = new TransactionAdapter(expenses);

        transactionAdapter.setOnClickListener(transaction -> Toast.makeText(this, transaction.getLabel(), Toast.LENGTH_SHORT).show());

        expensesList.setAdapter(transactionAdapter);
    }
}
