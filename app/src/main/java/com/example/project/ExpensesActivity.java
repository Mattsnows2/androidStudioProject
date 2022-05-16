package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    RecyclerView expensesList;
    TransactionAdapter transactionAdapter;

    static final String EXTRA_Transaction_label = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_amount = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_currency = "com.example.project.TransactionActivity";
    //static final String EXTRA_Transaction_date = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_category = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_type = "com.example.project.TransactionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        expensesList = findViewById(R.id.expenses_list);
        expensesList.hasFixedSize();
        expensesList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> expenses = new ArrayList<>();
        expenses.add(new Transaction("KFC", 60, "€", R.drawable.email, "2"));

        transactionAdapter = new TransactionAdapter(expenses);

        transactionAdapter.setOnClickListener(transaction -> {
            Intent intent = new Intent(ExpensesActivity.this, TransactionActivity.class);
            intent.putExtra(EXTRA_Transaction_label, transaction.getLabel());
            intent.putExtra(EXTRA_Transaction_amount, String.valueOf(transaction.getAmount()));
            intent.putExtra(EXTRA_Transaction_currency, transaction.getCurrency());
            //intent.putExtra(EXTRA_Transaction_date, DateFormat.getDateInstance().format(transaction.getDate()));
            intent.putExtra(EXTRA_Transaction_category, transaction.getCategory());
            intent.putExtra(EXTRA_Transaction_type, "receipts");
            startActivity(intent);
        });

        expensesList.setAdapter(transactionAdapter);
    }
}
