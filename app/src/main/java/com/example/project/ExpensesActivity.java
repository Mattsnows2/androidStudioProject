package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    RecyclerView expensesList;
    TransactionAdapter transactionAdapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

        } else {
            Log.i("testConnection", "pas connécté");

        }
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        setContentView(R.layout.activity_expenses);

        expensesList = findViewById(R.id.expenses_list);
        expensesList.hasFixedSize();
        expensesList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> expenses = new ArrayList<>();
        Query receipts = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("expenses");
        receipts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnaphot : snapshot.getChildren()) {

                    expenses.add(new Transaction(postSnaphot.getValue().toString()));

                    System.out.println(postSnaphot.getValue().toString());

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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        /*expenses.add(new Transaction("KFC", 60, "€", R.drawable.email));*/


    }
}
