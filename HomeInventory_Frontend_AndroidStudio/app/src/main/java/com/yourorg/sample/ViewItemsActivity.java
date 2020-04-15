package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewItemsActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    TextView textView;
    EditText et1, et2;
    Button bt1, bt2;
    ListView lv1, lv2;
    ArrayList<String> arrayList1, arrayList2;
    ArrayAdapter<String> adapter1, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        // Sets local variables equal to layout objects
        et1 = findViewById(R.id.editText4);
        et2 = findViewById(R.id.editText6);
        bt1 = findViewById(R.id.button10);
        bt2 = findViewById(R.id.button12);
        lv1 = findViewById(R.id.listView_lv3);

        // Sets title to previously selected list
        textView = findViewById(R.id.textView19);
        String list = getIntent().getStringExtra("ListViewListName");
        textView.setText("List: " + list);

        // Sets the list view to add text to
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(ViewItemsActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);

        onBtnClick();
    }

    public void onBtnClick() {
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Gets text from user input
                String itemName = et1.getText().toString();
                String quantity = et2.getText().toString();
                // Checks if itemName and quantity are empty
                if (noItemError(itemName, quantity))
                Toast.makeText(ViewItemsActivity.this, "noItemError. Please input an item name and quantity.", Toast.LENGTH_LONG).show();

                // Adds itemName and quantity to the list view
                else {
                    arrayList1.add("Item: " + itemName + ", " + "Quantity: " + quantity);
                    adapter1.notifyDataSetChanged();
                }
            }
        });
    }

    // Called when the user taps the ADD USER button
    public void addUser(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        // Gets the list name the user is viewing
        String list = getIntent().getStringExtra("ListViewListName");
        // Sends the list name to the AddUserActivity
        intent.putExtra("ListViewListName", list);
        startActivity(intent);
    }
    // Displays message if missing item field submission
    public boolean noItemError(String itemName, String itemQuantity) {
        boolean result = false;
        if (itemName.length() == 0 || itemQuantity.length() == 0){
            result = true;
        }
        return result;
    }
}

