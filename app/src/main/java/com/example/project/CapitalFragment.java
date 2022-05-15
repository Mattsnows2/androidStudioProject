package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CapitalFragment extends ListFragment {

    RecyclerView fragment_transaction_list;
    TransactionAdapter transactionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fragment_transaction_list = findViewById(R.id.transactionList);
        fragment_transaction_list.hasFixedSize();
        fragment_transaction_list.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("food", 5, "€", R.drawable.rocket));

        transactionAdapter = new TransactionAdapter(transactions);

        transactionAdapter.setOnClickListener(transaction -> Toast.makeText(this, transaction.getLabel(), Toast.LENGTH_SHORT).show());

        fragment_transaction_list.setAdapter(transactionAdapter);
    }
}