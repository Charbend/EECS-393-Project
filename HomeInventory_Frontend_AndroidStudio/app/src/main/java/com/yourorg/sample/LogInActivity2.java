package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

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

    EditText et1, et2;
    Button bt;
    public static String emailEntered, passwordEntered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);

        et1 = findViewById(R.id.username2);
        et2 = findViewById(R.id.password4);
        bt = findViewById(R.id.loginButton2);
    }

    // Called when the user taps the LOG-IN button
    public void logInSuccess(final View view) {

        // Gets text from edit text fields
        final String email = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();

        // Checks for empty fields
        if (missingLogInError(email, password))
            Toast.makeText(LogInActivity2.this, "missingLogInError. Please fill all the fields.", Toast.LENGTH_LONG).show();
            // Checks for valid email
        else if (invalidLogInError(email))
            Toast.makeText(LogInActivity2.this, "invalidLogInError. Please enter a valid email.", Toast.LENGTH_LONG).show();
            // Starts next activity
        else {
            Intent intent = new Intent(this, HomePageActivity.class);
            final Intent stay = new Intent(this, LogInActivity2.class);

            emailEntered = email;
            passwordEntered = password;


            ///Network operations should be done in the background

            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {

                    try {

                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(LogInActivity2.this);
                        String url = "http://10.0.2.2:4000/users/" + emailEntered;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {


                                        if(!response.contains(emailEntered) || !response.contains(passwordEntered)) {
                                            Toast.makeText(LogInActivity2.this, "no user with this info found", Toast.LENGTH_LONG).show();
                                            startActivity(stay);

                                        }
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
            // Sends the email to the HomePageActivity
            intent.putExtra("loggedInUserEmail", email);
            startActivity(intent);


        }



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
