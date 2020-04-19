package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class LogInActivity2 extends AppCompatActivity {

    // Local variables to represent layout objects
    EditText et1, et2;
    Button bt;

    public static String emailEntered, passwordEntered;

    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads login layout
        setContentView(R.layout.activity_log_in2);

        // Sets local variables to actual layout objects
        et1 = findViewById(R.id.username2);
        et2 = findViewById(R.id.password4);
        bt = findViewById(R.id.loginButton2);
    }

    // Called when the user taps the LOG-IN button
    @SuppressLint("StaticFieldLeak")
    public void logInSuccess(final View view) {

        // Gets text entered from edit text fields
        final String email = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();

        // Checks for empty fields
        if (missingLogInError(email, password))
            Toast.makeText(LogInActivity2.this, "missingLogInError. Please fill all the fields.", Toast.LENGTH_LONG).show();
        // Checks for valid email
        else if (invalidLogInError(email))
            Toast.makeText(LogInActivity2.this, "invalidLogInError. Please enter a valid email.", Toast.LENGTH_LONG).show();
        // Else check enterd info to see if account is in database
        else {
            // Sets intent to go take user from login to home page
            Intent intent = new Intent(this, HomePageActivity.class);
            // Sets stay to reload login
            final Intent stay = new Intent(this, LogInActivity2.class);

            emailEntered = email;
            passwordEntered = password;

            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {

                    try {

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(LogInActivity2.this);
                        // Connects to the users collection of database, and gets entries with entered email through server.js running locally
                        String url = "http://10.0.2.2:4000/users/" + emailEntered;
                        // GET request to get user accounts from database
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {

                                        // Checks if returned response contains entered email and password
                                        if (!response.contains(emailEntered) || !response.contains(passwordEntered)) {
                                            // Displays error message indicating user info is not on file
                                            Toast.makeText(LogInActivity2.this, "no user with this info found", Toast.LENGTH_LONG).show();
                                            // Deletes entered info, to start a new log in attempt
                                            startActivity(stay);
                                        }
                                        // Else user info is on file
                                        else {
                                            Toast.makeText(LogInActivity2.this, "success!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LogInActivity2.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(stringRequest);
                    } catch (Exception ex) {
                        Toast.makeText(LogInActivity2.this, ex.toString(), Toast.LENGTH_LONG).show();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                }
            }.execute();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Sends the logged in user email to the HomePageActivity
            intent.putExtra("loggedInUserEmail", email);
            // Starts the intent to go from login screen to home page
            startActivity(intent);
        }
    }

    // Displays message if missing log in field submission
    public boolean missingLogInError(String enteredEmail, String enteredPassword) {
        boolean result = false;
        // If no entry for email and password, return true that there is a missingLogInError
        if (enteredEmail.length() == 0 || enteredPassword.length() == 0) {
            result = true;
        }
        return result;
    }

    // Displays message if invalid email submission
    public boolean invalidLogInError(String enteredEmail) {
        boolean result = false;
        // If invalid email format, return true that there is an invalidLogInError
        if (!(!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches())) {
            result = true;
        }
        return result;
    }

}