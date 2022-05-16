package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ReceiptsActivity extends AppCompatActivity {

    RecyclerView receiptsList;
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
        setContentView(R.layout.activity_receipts);

        receiptsList = findViewById(R.id.receipts_list);
        receiptsList.hasFixedSize();
        receiptsList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Transaction> receipts2 = new ArrayList<>();
        /* receipts.add(new Transaction("cine", 16, "dkk", R.drawable.password));*/


        Query receipts = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts");
        receipts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnaphot : snapshot.getChildren()) {

                    receipts2.add(new Transaction(postSnaphot.getValue().toString()));

                    System.out.println(postSnaphot.getValue().toString());

                    transactionAdapter = new TransactionAdapter(receipts2);

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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
