package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiptsActivity extends AppCompatActivity {

    RecyclerView receiptsList;
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
        setContentView(R.layout.activity_receipts);

        receiptsList = findViewById(R.id.receipts_list);
        receiptsList.hasFixedSize();
        receiptsList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> receipts = new ArrayList<>();
        receipts.add(new Transaction("cine", 16, "dkk", R.drawable.password, "1"));

        transactionAdapter = new TransactionAdapter(receipts);

        transactionAdapter.setOnClickListener(transaction -> {
            Intent intent = new Intent(ReceiptsActivity.this, TransactionActivity.class);
            intent.putExtra(EXTRA_Transaction_label, transaction.getLabel());
            intent.putExtra(EXTRA_Transaction_amount, String.valueOf(transaction.getAmount()));
            intent.putExtra(EXTRA_Transaction_currency, transaction.getCurrency());
            //intent.putExtra(EXTRA_Transaction_date, transaction.getText());
            intent.putExtra(EXTRA_Transaction_category, transaction.getCategory());
            intent.putExtra(EXTRA_Transaction_type, "expenses");

            startActivity(intent);
        });

        receiptsList.setAdapter(transactionAdapter);
    }
}
