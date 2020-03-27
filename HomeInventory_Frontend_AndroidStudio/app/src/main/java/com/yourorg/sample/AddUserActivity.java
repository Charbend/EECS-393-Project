package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddUserActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        textView = findViewById(R.id.textView27);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String list = bundle.getString("ListViewListName");
        textView.setText(list);
    }

    /** Called when the user taps the Price Book button */
    public void toList(View view) {
        Intent intent = new Intent(this, ViewItemsActivity.class);
        startActivity(intent);
    }
}
