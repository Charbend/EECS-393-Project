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
    public void setAddUser(View view) {

        // Gets text from edit text fields
        String enteredEmail = emailInput.getText().toString();
        String enteredName = nameInput.getText().toString();

        // Checks for empty fields
        if (enteredEmail.length() == 0 || enteredName.length() == 0)
            noSecondaryUserError();
        // Checks for valid email
        else if (!isValidEmail(enteredEmail))
            invalidSecondaryUserError();
        // Returns to previous activity
        else {
            finish();
            //sendEmail(email, name);
            //Intent intent = new Intent(this, ViewItemsActivity.class);
            //startActivity(intent);
        }
    }

    // NOT WORKING, supposed to send email to the added user
    /* Called when the user taps the Add User button */
    protected void sendEmail(String toEmail, String toName) {
        Log.i("Send email", "");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setDataAndType(Uri.parse("mailto:"), "text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, toEmail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "You have been invited to a HomeInventory List");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "You have been invited to a HomeInventory List");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AddUserActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(this, ViewItemsActivity.class);
        //startActivity(intent);
    }

    // Checks if email is valid
    public boolean isValidEmail(CharSequence enteredEmail) {
        return (!TextUtils.isEmpty(enteredEmail) && Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches());
    }

    // Added below to display message if missing fields
    public void noSecondaryUserError() {
        Toast.makeText(AddUserActivity.this, "noSecondaryUserError. Please fill all the fields.", Toast.LENGTH_LONG).show();
    }

    // Added below to display message if invalid email submission
    public void invalidSecondaryUserError() {
        Toast.makeText(AddUserActivity.this, "invalidSecondaryUserError. Please enter a valid email.", Toast.LENGTH_LONG).show();
    }
}
