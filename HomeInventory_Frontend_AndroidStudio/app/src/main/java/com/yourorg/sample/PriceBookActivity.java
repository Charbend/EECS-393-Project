package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PriceBookActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    EditText et1, et2, et3, et4, et5;
    Button bt;
    ListView lv1;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_book);

        // Sets local variables equal to layout objects
        et1 = findViewById(R.id.editText5);
        et2 = findViewById(R.id.editText7);
        et3 = findViewById(R.id.editText8);
        et4 = findViewById(R.id.editText11);
        et5 = findViewById(R.id.editText12);
        bt = findViewById(R.id.button11);
        lv1 = findViewById(R.id.listView_lv5);

        // Sets the list view to add text to
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);

        onBtnClick();

    }
    // Puts user entered data into list view once button is clicked
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Gets text from edit text fields
                String itemEntry = et1.getText().toString();
                String quantityEntry = et2.getText().toString();
                String priceEntry = et3.getText().toString();
                String locationEntry = et4.getText().toString();
                String categoryEntry = et5.getText().toString();

                // Checks for empty fields
                if (itemEntry.length() == 0 || quantityEntry.length() == 0 || priceEntry.length() == 0
                        || locationEntry.length() == 0 || categoryEntry.length() == 0)
                    noPriceBookItemError();
                else {
                    // MIGHT HAVE TO CHANGE THIS FOR MONGODB
                    // Adds the text to the list view
                    arrayList1.add("Item: " + itemEntry + ", " + "Quantity: " + quantityEntry + ", " + "Price: " +
                            priceEntry + ", " + "Location Purchased: " + locationEntry + ", " + "Category: "
                            + categoryEntry);
                    adapter1.notifyDataSetChanged();
                }
            }
        });
    }

    // Displays message if missing price book field submission
    public void noPriceBookItemError() {
        Toast.makeText(PriceBookActivity.this, "noPriceBookItemError. Please fill all the fields ", Toast.LENGTH_LONG).show();
    }
}
