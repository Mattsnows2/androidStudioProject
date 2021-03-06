package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button receipts_btn;
    Button expenses_btn;
    TextView capital;
   // UserViewModel mUserViewModel;
    private DatabaseReference mDatabase;

    static final String EXTRA_Transaction = "com.example.project.TransactionActivity";

    Toolbar toolbar;

    RecyclerView capitalList;
    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){

        }else{
            Log.i("testConnection","pas connécté");
        }
        super.onCreate(savedInstanceState);
        final String[] capital3 = new String[1];
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("capital").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    capital3[0] = String.valueOf(task.getResult().getValue());
                    capital = findViewById(R.id.capitalBtn);

                    capital.setText(capital3[0]);
                }
            }
        });

        setContentView(R.layout.activity_dashboard);

        receipts_btn = findViewById(R.id.receiptsBtn);
        receipts_btn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ReceiptsActivity.class);
            startActivity(intent);
        });
        expenses_btn = findViewById(R.id.expensesBtn);
        expenses_btn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ExpensesActivity.class);
            startActivity(intent);
        });

        capitalList = findViewById(R.id.transactionList);
        capitalList.hasFixedSize();
        capitalList.setLayoutManager(new LinearLayoutManager(this));



        ArrayList<Transaction> transactions = new ArrayList<>();
        Query receipts =mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts");
        receipts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnaphot: snapshot.getChildren()){

                    transactions.add(new Transaction(postSnaphot.getValue().toString()));

                    System.out.println(postSnaphot.getValue().toString());

                    transactionAdapter = new TransactionAdapter(transactions);


                    transactionAdapter.setOnClickListener(v -> {
                        Intent intent = new Intent(DashboardActivity.this, TransactionActivity.class);
           /* intent.putExtra(EXTRA_Transaction, Transaction.getText().toString());
            intent.putExtra(EXTRA_Transaction, transactionField.getText().toString());
            intent.putExtra(EXTRA_Transaction, transactionField.getText().toString());
            intent.putExtra(EXTRA_Transaction, transactionField.getText().toString());*/
                        startActivity(intent);
                    });
                    capitalList.setAdapter(transactionAdapter);

                    toolbar = findViewById(R.id.toolbar);
                    setSupportActionBar(toolbar);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*transactions.add(new Transaction("food", 5, "€", R.drawable.rocket));
        transactions.add(new Transaction("candy", 6, "€", R.drawable.rocket));
        transactions.add(new Transaction("soda", 7, "€", R.drawable.rocket));
        transactions.add(new Transaction("museum", 8, "€", R.drawable.rocket));
        transactions.add(new Transaction("flight", 9, "€", R.drawable.rocket));*/




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_favorite) {
            Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.action_settings) {
            Intent intent = new Intent(DashboardActivity.this, UserActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}


