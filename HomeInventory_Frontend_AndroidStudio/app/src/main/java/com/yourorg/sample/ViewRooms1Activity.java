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
import android.widget.Toast;

import java.util.ArrayList;


public class ViewRooms1Activity extends AppCompatActivity {

    EditText et;
    Button bt;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms1);

        et = findViewById(R.id.editText2);
        bt = findViewById(R.id.button8);
        lv = findViewById(R.id.listView_lv);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ViewRooms1Activity.this, android.R.layout.simple_list_item_2,
                android.R.id.text1, arrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Templistview = arrayList.get(i);
                Intent intent = new Intent( ViewRooms1Activity.this, ViewListsActivity.class);
                intent.putExtra("ListViewRoomName", Templistview);
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
                // Added below to display message if empty submission
                if (result.length() == 0)
                    noRoomError();
                else {
                    arrayList.add(result);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    // Added below to display message if empty submission
    public void noRoomError() {
       Toast.makeText(ViewRooms1Activity.this, "noRoomError. Please input a room name.", Toast.LENGTH_LONG).show();
    }
}
