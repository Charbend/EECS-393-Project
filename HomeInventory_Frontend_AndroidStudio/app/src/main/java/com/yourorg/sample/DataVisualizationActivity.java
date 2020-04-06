package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DataVisualizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_visualization);

        // Below is to add drop down menu
        Spinner dropdown = findViewById(R.id.spinner1);
        // Creates a list of items for the dropdown menu.
        String[] items = new String[]{"This Week", "This Month", "This Year"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        // Sets dropdown menu values
        dropdown.setAdapter(adapter);
    }

}
