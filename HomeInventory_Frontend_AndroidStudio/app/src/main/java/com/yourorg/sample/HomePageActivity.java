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

    // Called when the user taps the View Rooms button, transfers them to ViewRooms1Activity
    public void ViewRooms1(View view) {
        Intent intent = new Intent(this, ViewRooms1Activity.class);
        // Sends the email to viewRooms1Activity
        String email = getIntent().getStringExtra("loggedInUserEmail");
        intent.putExtra("loggedInEmail", email);
        startActivity(intent);

    }

    // Called when the user taps the Price Book button, transfers them to PriceBookActivity
    public void PriceBook(View view) {
        Intent intent = new Intent(this, PriceBookActivity.class);
        String email = getIntent().getStringExtra("loggedInUserEmail");
        intent.putExtra("loggedInEmail", email);
        startActivity(intent);
    }

    // Called when the user taps the Track Purchases button, transfers them to TrackPurchasesActivity
    public void TrackPurchases(View view) {
        Intent intent = new Intent(this, TrackPurchasesActivity.class);
        String email = getIntent().getStringExtra("loggedInUserEmail");
        intent.putExtra("loggedInEmail", email);
        startActivity(intent);
    }

    // Called when the user taps the Price Book button, transfers them to ShopActivity
    public void Shop(View view) {
        Intent intent = new Intent(this, ShopActivity.class);
        String email = getIntent().getStringExtra("loggedInUserEmail");
        intent.putExtra("loggedInEmail", email);
        startActivity(intent);
    }
}
