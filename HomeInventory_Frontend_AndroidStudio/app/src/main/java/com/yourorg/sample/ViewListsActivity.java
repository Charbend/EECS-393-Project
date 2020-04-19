package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewListsActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    TextView textView;
    EditText et;
    Button bt;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @SuppressLint("StaticFieldLeak")
    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads view lists layout
        setContentView(R.layout.activity_view_lists);

        // Sets local variables equal to layout objects
        et = findViewById(R.id.editText3);
        bt = findViewById(R.id.button9);
        lv = findViewById(R.id.listView_lv2);
        textView = findViewById(R.id.textView18);

        // Gets the room name the user clicked on from view rooms page
        String room = getIntent().getStringExtra("ListViewRoomName");
        // Sets the title to that room name
        textView.setText("Room: " + room);

        // Creates array list to add entries to
        arrayList = new ArrayList<String>();
        // Creates adapter to add entries to list view
        adapter = new ArrayAdapter<String>(ViewListsActivity.this, android.R.layout.simple_list_item_2,
                android.R.id.text1, arrayList);
        // Sets the adapter to the list view to add text to
        lv.setAdapter(adapter);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ViewListsActivity.this);
                    // Sets url to lists collection in database through server.js running locally
                    String url = "http://10.0.2.2:4000/lists/";
                    // GET request to get list entries the user already has stored in database
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    // Gets the logged in user email from view rooms page
                                    String userEmail = getIntent().getStringExtra("ListViewUserEmail");
                                    // Gets the list name from view rooms page
                                    String roomName = getIntent().getStringExtra("ListViewRoomName");

                                    // Parsing below
                                    // Splits response from server by open curly bracket
                                    String[] resp = response.split("\\{");
                                    // Creates array list named resp2
                                    ArrayList<String> resp2 = new ArrayList<>();
                                    // Loops through resp
                                    for (String str1 : resp) {
                                        // Splits response from server by comma
                                        String[] str1arr = str1.split(",");
                                        // Loops through str1arr
                                        for (String s1 : str1arr) {
                                            // Adds splitted parts to resp2 array list
                                            resp2.add(s1);
                                        }
                                    }

                                    // Creates array list used to store lists
                                    ArrayList<String> correct = new ArrayList<>();

                                    // Cycles through resp
                                    for (String s : resp) {
                                        // If resp contains the user's email and room name together
                                        if (s.contains(userEmail) && s.contains(roomName)) {

                                            // Set s to string in front of lists:
                                            s = s.split("list\":")[1];
                                            // Set s to string before comma
                                            s = s.split(",")[0];
                                            // Deletes slashes from s
                                            s = s.replace("\"", "");
                                            // Deletes curly bracket from s
                                            s = s.replace("}", "");
                                            // Deletes closed bracket from s, left with the name of the item
                                            s = s.replace("]", "");

                                            // Adds s to correct array list
                                            correct.add(s);
                                        }
                                    }

                                    // If anything is in correct array list
                                    if (correct.size() > 0) {
                                        // Loops though correct
                                        for (String str : correct) {
                                            // Add the strings to arrayList
                                            arrayList.add(str);
                                            // Adapter displays entries in list view
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                    // Else there were no lists for this user in the database, so display message indicating so
                                    else {
                                        Toast.makeText(ViewListsActivity.this, "no lists for this user", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(ViewListsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                } catch (Exception ex) {
                    //Toast.makeText(ViewListsActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
            }
        }.execute();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Creates on click listener for list view
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Gets list name clicked on by user
                String Templistview = arrayList.get(i);
                // Sets intent to take user from this view lists page to view items page
                Intent intent = new Intent(ViewListsActivity.this, ViewItemsActivity.class);
                // Gets user email from view rooms page
                String userEmail = getIntent().getStringExtra("ListViewUserEmail");
                // Gets the room name the user clicked on from view rooms page
                String roomName = getIntent().getStringExtra("ListViewRoomName");
                // Sends the list name the user clicked on to the view items page
                intent.putExtra("ListViewListName", Templistview);
                // Sends the room name the user clicked on in the view rooms page to the view items page
                intent.putExtra("ListViewRoomName", roomName);
                // Sends the users email to the view items page
                intent.putExtra("LoggedInEmail", userEmail);
                // Starts the intent
                startActivity(intent);
            }
        });
        // Goes to onBtnClick method when user adds a new entry
        onBtnClick();
    }

    // On list submission
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                // Gets list name the user entered from edit text fields
                final String listEntry = et.getText().toString();
                // Display message if empty list name submission
                if (noListError(listEntry))
                    Toast.makeText(ViewListsActivity.this, "noListError. Please input a list name.", Toast.LENGTH_LONG).show();
                    // Adds list name to the list view
                else {
                    // Add the strings to arrayList
                    arrayList.add(listEntry);
                    // Adapter displays entries in list view
                    adapter.notifyDataSetChanged();

                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... params) {

                            try {

                                // Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(ViewListsActivity.this);
                                // Connects to lists collection in database through server.js running locally
                                String url = "http://10.0.2.2:4000/lists";
                                // POST request to lists entries to the database
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {

                                                //Toast.makeText(ViewListsActivity.this, response, Toast.LENGTH_LONG).show();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Toast.makeText(ViewListsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    // Sends parameters to database to be added
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        // Gets user email from view rooms page
                                        String email = getIntent().getStringExtra("ListViewUserEmail");
                                        // Gets the list name the user clicked on from the view rooms page
                                        String roomName = getIntent().getStringExtra("ListViewRoomName");
                                        // Adds the list name entered as the list to database
                                        params.put("list", listEntry);
                                        // Adds user email as the creator to database
                                        params.put("creator ", email);
                                        // Adds the room name entered as the room to database
                                        params.put("room ", roomName);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //Toast.makeText(ViewListsActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                            }

                            //return textView.getText().toString();
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String result) {
                            //textView.setText(result);
                        }
                    }.execute();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // Called when the user taps the ADD USER button
    public void addUser(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        // Gets the user email from the view rooms page
        String email = getIntent().getStringExtra("ListViewUserEmail");
        // Gets the list name the user clicked on from the view rooms page is viewing
        String list = getIntent().getStringExtra("ListViewRoomName");
        // Sends the list name to the AddUserActivity
        intent.putExtra("ListViewRoomName", list);
        // Sends user email to AddUserActivity
        intent.putExtra("ListViewUserEmail", email);
        // Starts the intent
        startActivity(intent);
    }

    // Displays message if empty list submission
    public boolean noListError(String list) {
        boolean result = false;
        // If no entry for list name, then return true that there is a noListError
        if (list.length() == 0) {
            result = true;
        }
        return result;
    }
}
