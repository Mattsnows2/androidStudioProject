package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private void showSettings() {
        // Create the intent.
        Intent helpIntent = new Intent(this, SettingsActivity.class);
        // Start the HelpActivity.
        startActivity(helpIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        switch (item.getItemId()) {
            case R.id.action_settings:
                showSettings();
                return true;
            case R.id.action_language:
                Intent languageIntent = new
                        Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(languageIntent);
                return true;
            default:
                // Do nothing.
        }
        return super.onOptionsItemSelected(item);
    }
}