package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    TextView textView;
    EditText emailInput, nameInput;
    String email, name;
    Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        textView = findViewById(R.id.textView27);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String list = bundle.getString("ListViewListName");
        textView.setText(list);

        emailInput = findViewById(R.id.editText9);
        nameInput = findViewById(R.id.editText10);
        addUser = findViewById(R.id.button13);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                name = nameInput.getText().toString();
                sendEmail(email, name);

            }
        });
    }

    /** Called when the user taps the Add User button */
    public void toList(View view) {
        sendEmail(email, name);
        Intent intent = new Intent(this, ViewItemsActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Add User button */
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
        Intent intent = new Intent(this, ViewItemsActivity.class);
        startActivity(intent);
    }
}
