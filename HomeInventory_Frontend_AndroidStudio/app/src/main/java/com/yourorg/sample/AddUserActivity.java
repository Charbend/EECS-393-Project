package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class AddUserActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    TextView textView;
    EditText emailInput, nameInput;
    Button addUser;
    String collectionURL1, collectionURL2;

    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads add user layout
        setContentView(R.layout.activity_add_user);

        // Sets text view to text view in layout
        textView = findViewById(R.id.textView27);
        // Gets message sent by previous activity
        // Not sure if next two lines are needed
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        // Sets the title variables to the room the user is to be added to
        String roomTitle = getIntent().getStringExtra("ListViewRoomName");
        // Sets the title variables to the list the user is to be added to
        String listTitle = getIntent().getStringExtra("ListViewListName");


        // Sets title based on whether the user came from a room or a list
        // If a user came from a room, then roomTitle will not be null
        if (roomTitle != null) {
            // Sets current title to the room the user came from
            textView.setText("Room: " + roomTitle);
            // Used later to connect to rooms portion of database
            collectionURL1 = "rooms";
            // Used later to connect to specific room in rooms portion of database
            collectionURL2 = roomTitle;
        }
        // Else roomTitle will be null, so they came from a lsit
        else {
            // Sets current title to the list the user came from
            textView.setText("List: " + listTitle);
            // Used later to connect to lists portion of database
            collectionURL1 = "lists";
            // Used later to connect to specific list in lists portion of database
            collectionURL2 = listTitle;
        }

        // Sets local variables to the layout objects
        emailInput = findViewById(R.id.editText9);
        nameInput = findViewById(R.id.editText10);
        addUser = findViewById(R.id.button13);
    }

    // Called when the user taps the Add User button
    @SuppressLint("StaticFieldLeak")
    public boolean setAddUser(View view) {

        //initializing action complete value
        boolean result = false;

        // Gets email, and name from edit text fields
        final String enteredEmail = emailInput.getText().toString();
        String enteredName = nameInput.getText().toString();

        // If there are empty fields, display a toast indicating that
        if (noSecondaryUserError(enteredEmail, enteredName)) {
            Toast.makeText(AddUserActivity.this, "noSecondaryUserError. Please fill all the fields.", Toast.LENGTH_LONG).show();
            result = false;
        }
        // Else if there is an email in invalid format, display a toast indicating that
        else if (invalidSecondaryUserError(enteredEmail))
            Toast.makeText(AddUserActivity.this, "invalidSecondaryUserError. Please enter a valid email.", Toast.LENGTH_LONG).show();
            // Else, add the entered email to the room/list the user came from to the database
        else {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    try {
                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(AddUserActivity.this);
                        // Sets url to appropriate room or list the user came from to add user to through server.js running locally
                        String url = "http://10.0.2.2:4000/" + collectionURL1 + "/" + collectionURL2;
                        // PUT request to update the room or list with the added user email
                        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Toast.makeText(AddUserActivity.this, response, Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Toast.makeText(AddUserActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                            @Override
                            // Sends parameters to database
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                // Adds the entered email as a secondaryUser in the database
                                params.put("secondaryUser", enteredEmail);
                                return params;
                            }
                        };
                        queue.add(postRequest);

                    } catch (Exception ex) {
                        //Toast.makeText(AddUserActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                    }
                    //return textView.getText().toString();
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    //textView.setText(result);
                }
            }.execute();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = true;
            // Returns to previous activity
            finish();
        }
        //returning action complete value
        return result;
    }


    // NOT WORKING, supposed to send email to the added user
    /* Called when the user taps the Add User button */
    protected boolean sendEmail(String toEmail, String toName) {

        //initializing action complete value
        boolean result = false;

        Log.i("Send email", "");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setDataAndType(Uri.parse("mailto:"), "text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, toEmail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "You have been invited to a HomeInventory List");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "You have been invited to a HomeInventory List");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished sending email", "");
            result = true;
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            result = false;
            //Toast.makeText(AddUserActivity.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(this, ViewItemsActivity.class);
        //startActivity(intent);

        //returning action complete value
        return result;
    }

    // Displays message if missing fields
    public boolean noSecondaryUserError(String addedEmail, String addedName) {
        boolean result = false;
        // If no entry for email and name, return true that there is a noSecondaryUserError
        if (addedEmail.length() == 0 || addedName.length() == 0) {
            result = true;
        }
        return result;
    }

    // Displays message if invalid email submission
    public boolean invalidSecondaryUserError(CharSequence enteredEmail) {
        boolean result = false;
        // If invalid email format, return true that there is an invalidSecondaryUserError
        if (!(!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches())) {
            result = true;
        }
        return result;
    }

}
