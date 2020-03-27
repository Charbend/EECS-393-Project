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

    EditText et1, et2, et3;
    Button bt;
    ListView lv1, lv2, lv3;
    ArrayList<String> arrayList1, arrayList2, arrayList3;
    ArrayAdapter<String> adapter1, adapter2, adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_book);
        et1 = findViewById(R.id.editText5);
        et2 = findViewById(R.id.editText7);
        et3 = findViewById(R.id.editText8);
        bt = findViewById(R.id.button11);
        lv1 = findViewById(R.id.listView_lv5);
        lv2 = findViewById(R.id.listView_lv6);
        lv3 = findViewById(R.id.listView_lv7);

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

        onBtnClick();

    }
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
            }
        });
    }
}
