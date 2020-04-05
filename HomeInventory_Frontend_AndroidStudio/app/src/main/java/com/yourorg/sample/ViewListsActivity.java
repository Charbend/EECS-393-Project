package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListsActivity extends AppCompatActivity {

    TextView textView;
    EditText et;
    Button bt;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lists);

        et = findViewById(R.id.editText3);
        bt = findViewById(R.id.button9);
        lv = findViewById(R.id.listView_lv2);

        textView = findViewById(R.id.textView18);
        String room = getIntent().getStringExtra("ListViewRoomName");
        textView.setText("Room: " + room);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ViewListsActivity.this, android.R.layout.simple_list_item_2,
                android.R.id.text1, arrayList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Templistview = arrayList.get(i);
                Intent intent = new Intent( ViewListsActivity.this, ViewItemsActivity.class);
                intent.putExtra("ListViewListName", Templistview);
                startActivity(intent);
            }
        });
        onBtnClick();
    }

    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String result = et.getText().toString();
                // Added below to display message if empty list name submission
                if (result.length() == 0)
                    noListError();
                else {
                    arrayList.add(result);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    /** Called when the user taps the ADD USER button */
    public void addUser(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        String list = getIntent().getStringExtra("ListViewListName");
        intent.putExtra("ListViewListName", list);
        startActivity(intent);
    }

    // Added below to display message if empty list submission
    public void noListError() {
        Toast.makeText(ViewListsActivity.this, "noListError. Please input a list name.", Toast.LENGTH_LONG).show();
    }
}
