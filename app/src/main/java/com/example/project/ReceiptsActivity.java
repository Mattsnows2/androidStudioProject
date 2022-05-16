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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);

        receiptsList = findViewById(R.id.receipts_list);
        receiptsList.hasFixedSize();
        receiptsList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> receipts = new ArrayList<>();
        receipts.add(new Transaction("cine", 16, "dkk", R.drawable.password));

        transactionAdapter = new TransactionAdapter(receipts);

        transactionAdapter.setOnClickListener(v -> {
            Intent intent = new Intent(ReceiptsActivity.this, TransactionActivity.class);
            /*intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());
            intent.putExtra(EXTRA_PET_NAME, petNameField.getText().toString());*/
            startActivity(intent);
        });

        receiptsList.setAdapter(transactionAdapter);
    }
}
