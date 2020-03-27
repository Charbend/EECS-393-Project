package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class PriceBookActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5;
    Button bt;
    ListView lv1, lv2, lv3, lv4, lv5;
    ArrayList<String> arrayList1, arrayList2, arrayList3, arrayList4, arrayList5;
    ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4, adapter5;

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
        lv2 = findViewById(R.id.listView_lv6);
        lv3 = findViewById(R.id.listView_lv7);
        lv4 = findViewById(R.id.listView_lv8);
        lv5 = findViewById(R.id.listView_lv9);

        // Lets the user entered value be added to the list view (lv1)
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);

        arrayList2 = new ArrayList<String>();
        adapter2 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList2);
        lv2.setAdapter(adapter2);

        arrayList3 = new ArrayList<String>();
        adapter3 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList3);
        lv3.setAdapter(adapter3);

        arrayList4 = new ArrayList<String>();
        adapter4 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList4);
        lv4.setAdapter(adapter4);

        arrayList5 = new ArrayList<String>();
        adapter5 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList5);
        lv5.setAdapter(adapter5);

        onBtnClick();

    }
    // Puts user entered data into list view once button is clicked
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String result1 = et1.getText().toString();
                arrayList1.add(result1);
                adapter1.notifyDataSetChanged();

                String result2 = et2.getText().toString();
                arrayList2.add(result2);
                adapter2.notifyDataSetChanged();

                String result3 = et3.getText().toString();
                arrayList3.add(result3);
                adapter3.notifyDataSetChanged();

                String result4 = et4.getText().toString();
                arrayList4.add(result4);
                adapter4.notifyDataSetChanged();

                String result5 = et5.getText().toString();
                arrayList5.add(result5);
                adapter5.notifyDataSetChanged();
            }
        });
    }
}
