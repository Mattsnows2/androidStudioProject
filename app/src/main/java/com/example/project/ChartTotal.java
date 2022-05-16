package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

public class ChartTotal extends AppCompatActivity {
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Expense> listOfExpenses = (ArrayList<Expense>) args.getSerializable("ARRAYLIST");*/
        setContentView(R.layout.activity_charts);
        pieChart = findViewById(R.id.pieChart);
        //TEST
        ArrayList<Expense> listOfExpenses = new ArrayList<>();
        listOfExpenses.add(new Expense("FOOD", 100));
        listOfExpenses.add(new Expense("Car", 500));
        listOfExpenses.add(new Expense("Games", 200));
        setupPieChart();
        loadPieChartData(listOfExpenses);
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
            entries.add(new PieEntry((obj.getAmount()/total), obj.getType()));
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