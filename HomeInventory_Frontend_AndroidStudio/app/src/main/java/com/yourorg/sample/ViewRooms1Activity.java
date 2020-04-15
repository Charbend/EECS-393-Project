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

    // Local variables to represent layout objects
    EditText et;
    Button bt;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms1);

        // Sets local variables equal to layout objects
        et = findViewById(R.id.editText2);
        bt = findViewById(R.id.button8);
        lv = findViewById(R.id.listView_lv);

        // Sets the list view to add text to
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ViewRooms1Activity.this, android.R.layout.simple_list_item_2,
                android.R.id.text1, arrayList);
        lv.setAdapter(adapter);
        // Lets the user click on a room to get transferred to the ViewListsActivity for that room
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
                // Gets text from edit text fields
                String roomName = et.getText().toString();
                // Added below to display message if empty submission
                if (noRoomError(roomName))
                Toast.makeText(ViewRooms1Activity.this, "noRoomError. Please input a room name.", Toast.LENGTH_LONG).show();

                // Adds room name to the room view
                else {
                    arrayList.add(roomName);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    // Displays message if empty submission
    public boolean noRoomError(String name) {
        boolean result = false;
        if (name.length() == 0){
            result = true;
        }
        return result;
    }
}

