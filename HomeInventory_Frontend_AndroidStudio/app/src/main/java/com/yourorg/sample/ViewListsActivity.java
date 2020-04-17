package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

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
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ViewListsActivity.this);

                    String url = "http://10.0.2.2:4000/lists/";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    String userEmail = getIntent().getStringExtra("ListViewUserEmail");
                                    String roomName = getIntent().getStringExtra("ListViewRoomName");
                                    String[] resp = response.split("\\{");
                                    ArrayList<String> resp2 = new ArrayList<>();
                                    for (String str1 : resp) {
                                        String[] str1arr = str1.split(",");
                                        for (String s1 : str1arr) {
                                            resp2.add(s1);
                                        }
                                    }
                                    ArrayList<String> correct = new ArrayList<>();
                                    for (String s : resp) {
                                        if (s.contains(userEmail) && s.contains(roomName)) {
                                            s = s.split("list\":")[1];
                                            s = s.split(",")[0];
                                            s = s.replace("\"", "");
                                            s = s.replace("}", "");

                                            correct.add(s);
                                        }
                                    }


                                    if (correct.size() > 0) {
                                        for (String str : correct) {
                                            arrayList.add(str);
                                            adapter.notifyDataSetChanged();
                                        }
                                        //Toast.makeText(ViewListsActivity.this, correct.toString(), Toast.LENGTH_LONG).show();

                                    } else {
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
        // Lets the user click on a list to get transferred to the ViewItemsActivity for that list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Templistview = arrayList.get(i);
                Intent intent = new Intent(ViewListsActivity.this, ViewItemsActivity.class);
                String userEmail = getIntent().getStringExtra("ListViewUserEmail");
                intent.putExtra("ListViewListName", Templistview);
                intent.putExtra("LoggedInEmail", userEmail);
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
                final String listEntry = et.getText().toString();
                // Added below to display message if empty list name submission
                if (noListError(listEntry))
                    Toast.makeText(ViewListsActivity.this, "noListError. Please input a list name.", Toast.LENGTH_LONG).show();

                    // Adds list name to the list view
                else {
                    arrayList.add(listEntry);
                    adapter.notifyDataSetChanged();
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... params) {

                            try {


                                // Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(ViewListsActivity.this);
                                String url = "http://10.0.2.2:4000/lists";
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {

                                                //textView.setText("Response is: " + response);
                                                //Toast.makeText(ViewListsActivity.this, response, Toast.LENGTH_LONG).show();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //textView.setText("you suck at comp sci");
                                        //Toast.makeText(ViewListsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        String email = getIntent().getStringExtra("ListViewUserEmail");
                                        params.put("list", listEntry);
                                        params.put("creator ", email);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //textView.setText(ex.toString());
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

        //gets user email
        String email = getIntent().getStringExtra("ListViewUserEmail");
        // Gets the list name the user is viewing
        String list = getIntent().getStringExtra("ListViewRoomName");
        // Sends the list name to the AddUserActivity
        intent.putExtra("ListViewListName", list);
        //sends user email to adduseracctivity
        intent.putExtra("ListViewUserEmail", email);
        startActivity(intent);
    }

    // Displays message if empty list submission
    public boolean noListError(String list) {
        boolean result = false;
        if (list.length() == 0) {
            result = true;
        }
        return result;
    }
}
