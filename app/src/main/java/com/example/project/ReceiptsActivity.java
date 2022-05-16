package com.example.project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiptsActivity extends AppCompatActivity {

    RecyclerView receiptsList;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        receiptsList = findViewById(R.id.receipts_list);
        receiptsList.hasFixedSize();
        receiptsList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> receipts = new ArrayList<>();
        receipts.add(new Transaction("cine", 16, "dkk", R.drawable.password));

        transactionAdapter = new TransactionAdapter(receipts);

        transactionAdapter.setOnClickListener(transaction -> Toast.makeText(this, transaction.getLabel(), Toast.LENGTH_SHORT).show());

        receiptsList.setAdapter(transactionAdapter);
    }
}
