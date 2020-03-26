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
    EditText et;
    Button bt;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        et = (EditText) findViewById(R.id.editText3) ;
        bt = (Button) findViewById(R.id.button9);
        lv = (ListView) findViewById(R.id.listView_lv2);

        textView = (TextView)findViewById(R.id.textView18);
        String room = getIntent().getStringExtra("ListViewRoomName");
        textView.setText(room);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ViewItemsActivity.this, android.R.layout.simple_list_item_1,
                arrayList);
        lv.setAdapter(adapter);
        onBtnClick();
    }

    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String result = et.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
