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

    // Local variables to represent layout objects
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

        // Sets local variables equal to layout objects
        et = findViewById(R.id.editText3);
        bt = findViewById(R.id.button9);
        lv = findViewById(R.id.listView_lv2);

        // Sets title to previously selected room
        textView = findViewById(R.id.textView18);
        String room = getIntent().getStringExtra("ListViewRoomName");
        textView.setText("Room: " + room);

        // Sets the list view to add text to
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ViewListsActivity.this, android.R.layout.simple_list_item_2,
                android.R.id.text1, arrayList);
        lv.setAdapter(adapter);
        // Lets the user click on a list to get transferred to the ViewItemsActivity for that list
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
                // Gets text from edit text fields
                String listEntry = et.getText().toString();
                // Added below to display message if empty list name submission
                if (noListError(listEntry))
                Toast.makeText(ViewListsActivity.this, "noListError. Please input a list name.", Toast.LENGTH_LONG).show();

                // Adds list name to the list view
                else {
                    arrayList.add(listEntry);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    // Called when the user taps the ADD USER button
    public void addUser(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        // Gets the room name the user is viewing
        String room = getIntent().getStringExtra("ListViewRoomName");
        // Sends the room name to the AddUserActivity
        intent.putExtra("ListViewRoomName", room);
        startActivity(intent);
    }

    // Displays message if empty list submission
    public boolean noListError(String list) {
        boolean result = false;
        if (list.length() == 0){
            result = true;
        }
        return result;
    }
}
