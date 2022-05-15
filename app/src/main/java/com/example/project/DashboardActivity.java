package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button capital_btn;
    Button receipts_btn;
    Button expenses_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        capital_btn = findViewById(R.id.capitalBtn);
        receipts_btn = findViewById(R.id.receiptsBtn);
        expenses_btn = findViewById(R.id.expensesBtn);

        NavController navController = Navigation.findNavController(this, R.id.containerFragment);

        capital_btn.setOnClickListener(v-> navController.navigate(R.id.capitalFragment));
        receipts_btn.setOnClickListener(v-> navController.navigate(R.id.capitalFragment));
        expenses_btn.setOnClickListener(v-> navController.navigate(R.id.capitalFragment));


    }
}


