package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrackPurchasesActivity extends AppCompatActivity {

    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads track purchases layout
        setContentView(R.layout.activity_track_purchases);
    }

    // Called when the user taps the Data Visualization button
    public void DataVisualization(View view) {
        // Sets intent to take user from track purchases to data visualization screen
        Intent intent = new Intent(this, DataVisualizationActivity.class);
        // Starts the intent
        startActivity(intent);
    }
}
