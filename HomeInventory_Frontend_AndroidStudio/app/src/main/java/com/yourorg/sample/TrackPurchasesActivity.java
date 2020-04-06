package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrackPurchasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_purchases);
    }

    // Called when the user taps the Data Visualization button, transfers them to DataVisualizationActivity
    public void DataVisualization(View view) {
        Intent intent = new Intent(this, DataVisualizationActivity.class);
        startActivity(intent);
    }
}
