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

public class LogInActivity2 extends AppCompatActivity {

    EditText et1, et2;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);

        et1 = findViewById(R.id.username2);
        et2 = findViewById(R.id.password4);
        bt = findViewById(R.id.loginButton2);
    }

    // Called when the user taps the LOG-IN button
    public void logInSuccess(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);

        // Gets text from edit text fields
        String email = et1.getText().toString();
        String password = et2.getText().toString();

        // Checks for empty fields
        if (email.length() == 0 || password.length() == 0) {
            missingLogInError(email, password);
            Toast.makeText(LogInActivity2.this, "missingLogInError. Please fill all the fields.", Toast.LENGTH_LONG).show();
        }
            // Checks for valid email
        else if (invalidLogInError(email))
        Toast.makeText(LogInActivity2.this, "invalidLogInError. Please enter a valid email.", Toast.LENGTH_LONG).show();
            // Starts next activity
        else
            startActivity(intent);
    }

    // Displays message if missing log in field submission
    public boolean missingLogInError(String enteredEmail, String enteredPassword) {
        boolean result = false;
        if (enteredEmail.length() == 0 || enteredPassword.length() == 0) {
            result = true;
        }
        return result;
    }

    // Displays message if invalid log in field submission
    public boolean invalidLogInError(String enteredEmail) {
        boolean result = false;
        if (!(!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches())) {
            result = true;
        }
        return result;
    }

}
