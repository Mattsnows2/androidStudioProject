package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it

        Spinner currency = (Spinner) findViewById(R.id.spinner);
        Switch sw = (Switch)findViewById(R.id.switch1);
        Button button = (Button)findViewById(R.id.button);
        //Setting the ArrayAdapter data on the Spinner

        ArrayAdapter<CharSequence> arad_currency = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        arad_currency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        currency.setAdapter(arad_currency);




        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (sw.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                };
            }
        });



    }

}
