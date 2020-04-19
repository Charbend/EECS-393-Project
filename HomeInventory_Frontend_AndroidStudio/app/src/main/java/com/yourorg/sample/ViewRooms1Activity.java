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


public class ViewRooms1Activity extends AppCompatActivity {

    // Local variables to represent layout objects
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
        // Loads view rooms layout
        setContentView(R.layout.activity_view_rooms1);

        // Sets local variables equal to layout objects
        et = findViewById(R.id.editText2);
        bt = findViewById(R.id.button8);
        lv = findViewById(R.id.listView_lv);

        // Creates array list to add entries to
        arrayList = new ArrayList<String>();
        // Creates adapter to add entries to list view
        adapter = new ArrayAdapter<String>(ViewRooms1Activity.this, android.R.layout.simple_list_item_2,
                android.R.id.text1, arrayList);
        // Sets the adapter to the list view to add text to
        lv.setAdapter(adapter);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ViewRooms1Activity.this);
                    // Sets url to rooms collection in database through server.js running locally
                    String url = "http://10.0.2.2:4000/rooms/";
                    // GET request to get room entries the user already has stored in database
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    // Gets the logged in user email from home page
                                    String userEmail = getIntent().getStringExtra("loggedInEmail");

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

                                    // Creates array list used to store rooms
                                    ArrayList<String> correct = new ArrayList<>();

                                    // Cycles through resp
                                    for (String s : resp) {
                                        // If resp contains the user's email
                                        if (s.contains(userEmail)) {

                                            // Set s to string in front of rooms:
                                            s = s.split("room\":")[1];
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
                                    // Else there were no rooms for this user in the database, so display message indicating so
                                    else {
                                        Toast.makeText(ViewRooms1Activity.this, "no rooms for this user", Toast.LENGTH_LONG).show();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(ViewRooms1Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                } catch (Exception ex) {
                    //Toast.makeText(ViewRooms1Activity.this, ex.toString(), Toast.LENGTH_LONG).show();
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
                // Sets intent to take user from this view rooms page to view lists page
                Intent intent = new Intent(ViewRooms1Activity.this, ViewListsActivity.class);
                // Gets user email from home page
                String userEmail = getIntent().getStringExtra("loggedInEmail");
                // Sends the room name the user clicked on in the view rooms page to the view lists page
                intent.putExtra("ListViewRoomName", Templistview);
                // Sends the users email to the view lists page
                intent.putExtra("ListViewUserEmail", userEmail);
                // Starts the intent
                startActivity(intent);
            }
        });
        // Goes to onBtnClick method when user adds a new entry
        onBtnClick();
    }

    // On room submission
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {

                // Gets room name the user entered from edit text fields
                final String roomName = et.getText().toString();
                // Displays message if empty submission
                if (noRoomError(roomName))
                    Toast.makeText(ViewRooms1Activity.this, "noRoomError. Please input a room name.", Toast.LENGTH_LONG).show();
                    // Adds room name to the room view
                else {
                    // Add the room to arrayList
                    arrayList.add(roomName);
                    // Adapter displays entries in list view
                    adapter.notifyDataSetChanged();

                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... params) {

                            try {

                                // Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(ViewRooms1Activity.this);
                                // Connects to rooms collection in database through server.js running locally
                                String url = "http://10.0.2.2:4000/rooms";
                                // POST request to rooms entries to the database
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {

                                                //Toast.makeText(ViewRooms1Activity.this, response, Toast.LENGTH_LONG).show();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Toast.makeText(ViewRooms1Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    // Sends parameters to database to be added
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        // Gets user email from home page
                                        String email = getIntent().getStringExtra("loggedInEmail");
                                        // Adds the room name entered as the list to database
                                        params.put("room", roomName);
                                        // Adds user email as the creator to database
                                        params.put("creator ", email);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //Toast.makeText(ViewRooms1Activity.this, ex.toString(), Toast.LENGTH_LONG).show();
                            }

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

    // Displays message if empty submission
    public boolean noRoomError(String name) {
        boolean result = false;
        // If no entry for room name, then return true that there is a noRoomError
        if (name.length() == 0) {
            result = true;
        }
        return result;
    }
}

