package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

        transactionAdapter.setOnClickListener(v -> {
            Intent intent = new Intent(ExpensesActivity.this, TransactionActivity.class);
            /*intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());*/
            startActivity(intent);
        });

        expensesList.setAdapter(transactionAdapter);
    }
}
