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


public class SignUpActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    EditText et1, et2, et3, et4;
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

    }

    // Called when the user taps the SIGN UP button
    public void signUpSuccess(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);

        // Gets text from edit text fields
        String name = et1.getText().toString();
        String email = et2.getText().toString();
        String password = et3.getText().toString();
        String confirmPassword = et4.getText().toString();

        // Checks for empty fields and password equivalence
        if (missingSignUpError(name, email, password, confirmPassword))
            Toast.makeText(SignUpActivity.this, "missingSignUpError. Please fill all the fields.", Toast.LENGTH_LONG).show();

            // Checks for valid email
        else if (invalidSignUpError(email))
            Toast.makeText(SignUpActivity.this, "invalidSignUpError. Please enter a valid email.", Toast.LENGTH_LONG).show();
        else if (!password.equals(confirmPassword))
            Toast.makeText(SignUpActivity.this, "Passwords don't match! Please try again.", Toast.LENGTH_LONG).show();
        else
            startActivity(intent);
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
