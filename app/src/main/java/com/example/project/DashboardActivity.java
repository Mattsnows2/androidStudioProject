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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button receipts_btn;
    Button expenses_btn;
    TextView capital;
   // UserViewModel mUserViewModel;
    private DatabaseReference mDatabase;

    static final String EXTRA_Transaction_label = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_amount = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_currency = "com.example.project.TransactionActivity";
    //static final String EXTRA_Transaction_date = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_category = "com.example.project.TransactionActivity";
    static final String EXTRA_Transaction_type = "com.example.project.TransactionActivity";

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
        transactions.add(new Transaction("food", 5, "€", R.drawable.rocket, "category1"));
        transactions.add(new Transaction("candy", 6, "€", R.drawable.rocket, "bouffe"));
        transactions.add(new Transaction("soda", 7, "€", R.drawable.rocket, "category3"));
        transactions.add(new Transaction("museum", 8, "€", R.drawable.rocket, "category4"));
        transactions.add(new Transaction("flight", 9, "€", R.drawable.rocket, "category5"));

        transactionAdapter = new TransactionAdapter(transactions);

        transactionAdapter.setOnClickListener(transaction -> {
            Intent intent = new Intent(DashboardActivity.this, TransactionActivity.class);
            intent.putExtra(EXTRA_Transaction_label, transaction.getLabel());
            intent.putExtra(EXTRA_Transaction_amount, String.valueOf(transaction.getAmount()));
            intent.putExtra(EXTRA_Transaction_currency, transaction.getCurrency());
            //intent.putExtra(EXTRA_Transaction_date, DateFormat.getDateInstance().format(transaction.getDate()));
            intent.putExtra(EXTRA_Transaction_category, transaction.getCategory());
            //intent.putExtra(EXTRA_Transaction_type, String.valueOf(transaction.getType()));
            startActivity(intent);

        });

        capitalList.setAdapter(transactionAdapter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


