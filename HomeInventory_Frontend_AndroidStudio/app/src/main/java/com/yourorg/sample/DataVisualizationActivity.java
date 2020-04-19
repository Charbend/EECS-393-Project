package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DataVisualizationActivity extends AppCompatActivity {

    // Declares local textview objects
    TextView tv1, tv2, tv3;

    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads data visualization layout
        setContentView(R.layout.activity_data_visualization);

        // Was going to use display price book statistics in
        //tv1 = findViewById(R.id.textView30);
        //tv2 = findViewById(R.id.textView31);
        //tv3 = findViewById(R.id.textView34);

        // Below is to add drop down menu
        Spinner dropdown = findViewById(R.id.spinner1);
        // Creates a list of items for the dropdown menu.
        String[] items = new String[]{"This Week", "This Month", "This Year"};
        // Creates adapter for adding values
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        // Sets dropdown menu values
        dropdown.setAdapter(adapter);

    }

}
