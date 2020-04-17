package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class SignUpActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    EditText et1, et2, et3, et4;
    TextView textView;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Sets local variables equal to layout objects
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.email);
        et3 = findViewById(R.id.password2);
        et4 = findViewById(R.id.password3);
        bt = findViewById(R.id.button4);
        textView = findViewById(R.id.text);

    }

    // Called when the user taps the SIGN UP button
    public void signUpSuccess(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);

        // Gets text from edit text fields
        final String name = et1.getText().toString();
        final String email = et2.getText().toString();
        final String password = et3.getText().toString();
        final String confirmPassword = et4.getText().toString();

        // Checks for empty fields and password equivalence
        if (missingSignUpError(name, email, password, confirmPassword))
            Toast.makeText(SignUpActivity.this, "missingSignUpError. Please fill all the fields.", Toast.LENGTH_LONG).show();

            // Checks for valid email
        else if (!invalidSignUpError(email))
            Toast.makeText(SignUpActivity.this, "invalidSignUpError. Please enter a valid email.", Toast.LENGTH_LONG).show();
        else if (!password.equals(confirmPassword))
            Toast.makeText(SignUpActivity.this, "Passwords don't match! Please try again.", Toast.LENGTH_LONG).show();

        else {



            //Network operations should be done in the background

            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {

                    try {




                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                        String url = "http://10.0.2.2:4000/users";
                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {

                                        textView.setText("Response is: " + response);
                                        //Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_LONG).show();

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textView.setText("you suck at comp sci");
                            }
                        }){
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("password", password);
                            params.put("user", name);
                            params.put("email", email);

                            return params;
                        }
                    };
                    queue.add(postRequest);

                    } catch (Exception ex) {
                        textView.setText(ex.toString());
                    }


                    return textView.getText().toString();
                }

                @Override
                protected void onPostExecute(String result) {
                    textView.setText(result);
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

    // Displays message if missing sign up field submission
    public boolean missingSignUpError(String userName, String userEmail, String userPass, String userConfPass) {
        boolean result = false;
        if (userName.length() == 0 || userEmail.length() == 0 || userPass.length() == 0
                || userConfPass.length() == 0) {
            result = true;
        }
        return result;
    }

    // Displays message if invalid sign up field submission
    public boolean invalidSignUpError(CharSequence enteredEmail) {
        return (!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches());
    }

}
