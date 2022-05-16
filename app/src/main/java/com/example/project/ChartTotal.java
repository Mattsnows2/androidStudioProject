package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.NumberFormat;
import java.util.ArrayList;

public class ChartTotal extends AppCompatActivity {
    private PieChart pieChart;
    private DatabaseReference mDatabase;
    public  String[] capital5 = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){



        }else{
            Log.i("y2","pas connécté");
        }
        super.onCreate(savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance("https://projectbilancio-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
       /* Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Expense> listOfExpenses = (ArrayList<Expense>) args.getSerializable("ARRAYLIST");*/
        setContentView(R.layout.activity_charts);
        pieChart = findViewById(R.id.pieChart);
        //TEST

        final String[] capital3 = new String[1];

        Task<DataSnapshot> capital2 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child("categories").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                Task<DataSnapshot> capital4 =  mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("receipts").child("operation").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {

                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                           capital5[0] = String.valueOf(task.getResult().getValue());
                           Log.d("test",capital5[0]);
                        }
                    }
                });
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {

                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    capital3[0] = String.valueOf(task.getResult().getValue());
                    ArrayList<Expense> listOfExpenses = new ArrayList<>();
                    Log.d("test",capital5[0]);
                    listOfExpenses.add(new Expense(capital3[0] , 4d));
                    listOfExpenses.add(new Expense("lksq,gkq", 500d));
                    listOfExpenses.add(new Expense("Games", 200d));
                    setupPieChart();
                    loadPieChartData(listOfExpenses);
                }
            }
        });




    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Total Expenses");
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setEnabled(true);
    }

    private void loadPieChartData(ArrayList<Expense> list) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        float total = 0;
        for (Expense obj:list) {
            total += obj.getAmount();
        }
        for (Expense obj:list) {
            entries.add(new PieEntry((float) (obj.getAmount()/total), obj.getType()));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutExpo);
    }

}