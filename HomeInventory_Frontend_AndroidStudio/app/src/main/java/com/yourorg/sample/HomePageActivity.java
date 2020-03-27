package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    /** Called when the user taps the View Rooms button */
    public void ViewRooms1(View view) {
        Intent intent = new Intent(this, ViewRooms1Activity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Price Book button */
    public void PriceBook(View view) {
        Intent intent = new Intent(this, PriceBookActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Price Book button */
    public void Search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
