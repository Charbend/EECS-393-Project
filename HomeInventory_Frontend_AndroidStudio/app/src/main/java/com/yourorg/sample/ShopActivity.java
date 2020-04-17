package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Below is to add drop down menu
        Spinner dropdown = findViewById(R.id.spinner2);
        // Creates a list of items for the dropdown menu.
        String[] items = new String[]{"Amazon", "Walmart"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        // Sets dropdown menu values
        dropdown.setAdapter(adapter);
    }
}
