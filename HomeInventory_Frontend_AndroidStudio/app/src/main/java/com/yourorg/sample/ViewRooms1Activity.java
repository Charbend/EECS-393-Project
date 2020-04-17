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
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ViewRooms1Activity.this);

                    String url = "http://10.0.2.2:4000/rooms/" ;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    String userEmail = getIntent().getStringExtra("loggedInEmail");
                                    String[] resp = response.split("\\{");
                                    ArrayList<String> resp2 = new ArrayList<>();
                                    for(String str1 : resp) {
                                        String[] str1arr = str1.split(",");
                                        for(String s1 : str1arr) {
                                            resp2.add(s1);
                                        }
                                    }
                                    ArrayList<String> correct = new ArrayList<>();
                                    for (String s : resp) {
                                        if(s.contains(userEmail)) {
                                            s = s.split("room\":")[1];
                                            s = s.split(",")[0];
                                            s = s.replace("\"", "");
                                            s = s.replace("}", "");

                                            correct.add(s);
                                        }
                                    }



                                    if(correct.size() > 0) {
                                        for(String str : correct) {
                                            arrayList.add(str);
                                            adapter.notifyDataSetChanged();
                                        }
                                        //Toast.makeText(ViewRooms1Activity.this, correct.toString(), Toast.LENGTH_LONG).show();

                                    }
                                    else {
                                        Toast.makeText(ViewRooms1Activity.this, "no rooms for this user", Toast.LENGTH_LONG).show();
                                    }



                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ViewRooms1Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                } catch (Exception ex) {

                    Toast.makeText(ViewRooms1Activity.this, ex.toString(), Toast.LENGTH_LONG).show();

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
        // Lets the user click on a room to get transferred to the ViewListsActivity for that room
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Templistview = arrayList.get(i);
                Intent intent = new Intent( ViewRooms1Activity.this, ViewListsActivity.class);
                String userEmail = getIntent().getStringExtra("loggedInEmail");
                intent.putExtra("ListViewRoomName", Templistview);
                intent.putExtra("ListViewUserEmail", userEmail);
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
                final String roomName = et.getText().toString();
                // Added below to display message if empty submission
                if (noRoomError(roomName))
                Toast.makeText(ViewRooms1Activity.this, "noRoomError. Please input a room name.", Toast.LENGTH_LONG).show();

                // Adds room name to the room view
                else {
                    arrayList.add(roomName);
                    adapter.notifyDataSetChanged();
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... params) {

                            try {




                                // Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(ViewRooms1Activity.this);
                                String url = "http://10.0.2.2:4000/rooms";
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {

                                                //textView.setText("Response is: " + response);
                                                Toast.makeText(ViewRooms1Activity.this, response, Toast.LENGTH_LONG).show();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //textView.setText("you suck at comp sci");
                                        Toast.makeText(ViewRooms1Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams()
                                    {
                                        Map<String, String> params = new HashMap<String, String>();
                                        String email = getIntent().getStringExtra("loggedInEmail");
                                        params.put("room", roomName);
                                        params.put("creator ", email);
                                       // params.put("email", email);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //textView.setText(ex.toString());
                                Toast.makeText(ViewRooms1Activity.this, ex.toString(), Toast.LENGTH_LONG).show();
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

    // Displays message if empty submission
    public boolean noRoomError(String name) {
        boolean result = false;
        if (name.length() == 0){
            result = true;
        }
        return result;
    }
}

