package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewItemsActivity extends AppCompatActivity {

    TextView textView;
    EditText et1, et2;
    Button bt;
    ListView lv1, lv2;
    ArrayList<String> arrayList1, arrayList2;
    ArrayAdapter<String> adapter1, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        et1 = findViewById(R.id.editText4);
        et2 = findViewById(R.id.editText6);
        bt = findViewById(R.id.button10);
        lv1 = findViewById(R.id.listView_lv3);
        lv2 = findViewById(R.id.listView_lv4);

        textView = findViewById(R.id.textView19);
        String room = getIntent().getStringExtra("ListViewListName");
        textView.setText(room);

        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(ViewItemsActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);

        arrayList2 = new ArrayList<String>();
        adapter2 = new ArrayAdapter<String>(ViewItemsActivity.this, android.R.layout.simple_list_item_1,
                arrayList2);
        lv2.setAdapter(adapter2);

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
            }
        });
    }
}

