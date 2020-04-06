package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    EditText et1, et2;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.password);
        bt = findViewById(R.id.loginButton);
    }

    // Called when the user taps the LOG-IN button
    public void logInSuccess(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);

        // Gets text from edit text fields
        String email = et1.getText().toString();
        String password = et2.getText().toString();

        // Checks for empty fields
        if (email.length() == 0 || password.length() == 0)
            missingLogInError();
        // Checks for valid email
        else if (!isValidEmail(email))
            invalidLogInError();
        // Starts next activity
        else
            startActivity(intent);
    }

    // Checks for valid email
    public boolean isValidEmail(CharSequence enteredEmail) {
        return (!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches());
    }

    // Displays message if missing log in field submission
    public void missingLogInError() {
        Toast.makeText(LogInActivity.this, "missingLogInError. Please fill all the fields.", Toast.LENGTH_LONG).show();
    }

    // Displays message if invalid log in field submission
    public void invalidLogInError() {
        Toast.makeText(LogInActivity.this, "invalidLogInError. Please enter a valid email.", Toast.LENGTH_LONG).show();
    }

}
