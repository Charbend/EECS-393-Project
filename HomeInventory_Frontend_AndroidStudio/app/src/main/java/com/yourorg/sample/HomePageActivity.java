package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends AppCompatActivity {

    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads home page layout upon screen creation
        setContentView(R.layout.activity_home_page);
    }

    // Called when the user taps the View Rooms button, transfers them to ViewRooms1Activity
    public void ViewRooms1(View view) {
        // Sets intent to go take user from this home page to view rooms
        Intent intent = new Intent(this, ViewRooms1Activity.class);
        // Gets the logged in user email from the previous page, login screen or sign up
        String email = getIntent().getStringExtra("loggedInUserEmail");
        // Sends the logged in user email to viewRooms1Activity
        intent.putExtra("loggedInEmail", email);
        // Starts intent
        startActivity(intent);

    }

    // Called when the user taps the Price Book button, transfers them to PriceBookActivity
    public void PriceBook(View view) {
        // Sets intent to go take user from this home page to price book
        Intent intent = new Intent(this, PriceBookActivity.class);
        // Gets the logged in user email from the previous page, login screen or sign up
        String email = getIntent().getStringExtra("loggedInUserEmail");
        // Sends the logged in user email to PriceBookActivity
        intent.putExtra("loggedInEmail", email);
        // Starts intent
        startActivity(intent);
    }

    // Called when the user taps the Track Purchases button, transfers them to TrackPurchasesActivity
    public void TrackPurchases(View view) {
        // Sets intent to go take user from this home page to track purchases
        Intent intent = new Intent(this, TrackPurchasesActivity.class);
        // Gets the logged in user email from the previous page, login screen or sign up
        String email = getIntent().getStringExtra("loggedInUserEmail");
        // Sends the logged in user email to the TrackPurchasesActivity
        intent.putExtra("loggedInEmail", email);
        // Starts intent
        startActivity(intent);
    }

    // Called when the user taps the Price Book button, transfers them to ShopActivity
    public void Shop(View view) {
        // Sets intent to go take user from this home page to shop
        Intent intent = new Intent(this, ShopActivity.class);
        // Gets the logged in user email from the previous page, login screen or sign up
        String email = getIntent().getStringExtra("loggedInUserEmail");
        // Sends the logged in user email to the ShopActivity
        intent.putExtra("loggedInEmail", email);
        // Starts intent
        startActivity(intent);
    }
}