package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    TextView textView;
    EditText emailInput, nameInput;
    Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        textView = findViewById(R.id.textView27);
        // Gets message sent by previous activity
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        // Sets the title variables to the room the user is to be added to
        String roomTitle = bundle.getString("ListViewRoomName");
        // Sets the title variables to the list the user is to be added to
        String listTitle = bundle.getString("ListViewListName");

        // Sets title based on
        if (roomTitle != null)
            textView.setText("Room: " + roomTitle);
        else
            textView.setText("List: " + listTitle);

        // Sets local variables to the layout objects
        emailInput = findViewById(R.id.editText9);
        nameInput = findViewById(R.id.editText10);
        addUser = findViewById(R.id.button13);
        /*addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                name = nameInput.getText().toString();
                sendEmail(email, name);

            }
        });*/
    }

    // Called when the user taps the Add User button
    public boolean setAddUser(View view) {
        
        //initializing action complete value
        boolean result = false;
        
        // Gets text from edit text fields
        String enteredEmail = emailInput.getText().toString();
        String enteredName = nameInput.getText().toString();

        // Checks for empty fields
        if (noSecondaryUserError(enteredEmail) || noSecondaryUserError(enteredName))
            result = false;
        // Checks for valid user by checking for valid email
        else if (invalidSecondaryUserError()){
            result = false;
        }
        // Returns to previous activity
        else {
            result = sendEmail(email, name);
            //Intent intent = new Intent(this, ViewItemsActivity.class);
            //startActivity(intent);
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
            Toast.makeText(AddUserActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(this, ViewItemsActivity.class);
        //startActivity(intent);
        
        //returning action complete value 
        return result;
    }

    // Checks if email is valid
    public boolean isValidEmail(CharSequence enteredEmail) {
        return (!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches());
    }

    // Added below to display message if missing fields
    public boolean noSecondaryUserError(String text) {
        boolean result = false;
        if (text.length()==0){
            result = true;
            Toast.makeText(AddUserActivity.this, "noSecondaryUserError. Please fill all the fields.", Toast.LENGTH_LONG).show();
        }
        return result
    }

    // Added below to display message if invalid email submission
    public boolean invalidSecondaryUserError() {
        boolean result = false;
        if (!isValidEmail(enteredEmail)){
            result = true;
            Toast.makeText(AddUserActivity.this, "invalidSecondaryUserError. Please enter a valid email.", Toast.LENGTH_LONG).show();
        }
        return result;
    }
}
