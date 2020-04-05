package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.username2);
        et3 = findViewById(R.id.password2);
        et4 = findViewById(R.id.password3);
        bt = findViewById(R.id.button4);

    }

    /** Called when the user taps the SiGN UP button */
    public void signUpSuccess(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);

        String result1 = et1.getText().toString();
        String result2 = et2.getText().toString();
        String result3 = et3.getText().toString();
        String result4 = et4.getText().toString();

        // Added below to display message if empty sign up submission
        if (result1.length() == 0 || result2.length() == 0
                || result3.length() == 0 || result4.length() == 0)
            missingSignUpError();
        else
        startActivity(intent);
    }

    // Added below to display message if missing sign up field submission
    public void missingSignUpError() {
        Toast.makeText(SignUpActivity.this, "missingSignUpError. Please fill all the fields ", Toast.LENGTH_LONG).show();
    }
}
