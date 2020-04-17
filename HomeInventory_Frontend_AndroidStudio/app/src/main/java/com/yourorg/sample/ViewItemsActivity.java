package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

public class ViewItemsActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    TextView textView;
    EditText et1, et2;
    Button bt1, bt2;
    ListView lv1;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        // Sets local variables equal to layout objects
        et1 = findViewById(R.id.editText4);
        et2 = findViewById(R.id.editText6);
        bt1 = findViewById(R.id.button10);
        bt2 = findViewById(R.id.button12);
        lv1 = findViewById(R.id.listView_lv3);

        // Sets title to previously selected list
        textView = findViewById(R.id.textView19);
        String list = getIntent().getStringExtra("ListViewListName");
        textView.setText("List: " + list);

        // Sets the list view to add text to
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(ViewItemsActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ViewItemsActivity.this);

                    String url = "http://10.0.2.2:4000/items";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    String userEmail = getIntent().getStringExtra("LoggedInEmail");
                                    String listName = getIntent().getStringExtra("ListViewListName");
                                    String[] resp = response.split("\\{");
                                    ArrayList<String> resp2 = new ArrayList<>();
                                    for (String str1 : resp) {
                                        String[] str1arr = str1.split(",");
                                        for (String s1 : str1arr) {
                                            resp2.add(s1);
                                        }
                                    }
                                    ArrayList<String> correct = new ArrayList<>();


                                    for (int i = 0; i < resp.length; i++) {

                                        if (resp[i].contains(userEmail) && resp[i].contains(listName)) {

                                            String s = resp[i].split("itemname\":")[1];
                                            s = s.split(",")[0];
                                            s = s.replace("\"", "");
                                            s = s.replace("}", "");
                                            String s2 = resp[i].split("quantity \":")[1];
                                            s2 = s2.split(",")[0];
                                            s2 = s2.replace("\"", "");
                                            s2 = s2.replace("}", "");
                                            String s3 = "Item: " + s + ", " + "Quantity: " + s2;


                                            correct.add(s3);
                                        }
                                    }


                                    if (correct.size() > 0) {
                                        for (String str : correct) {
                                            arrayList1.add(str);
                                            adapter1.notifyDataSetChanged();
                                        }
                                        //Toast.makeText(ViewListsActivity.this, correct.toString(), Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(ViewItemsActivity.this, "no items for this user", Toast.LENGTH_LONG).show();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(ViewItemsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                } catch (Exception ex) {

                    //Toast.makeText(ViewItemsActivity.this, ex.toString(), Toast.LENGTH_LONG).show();

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

        onBtnClick();
    }

    public void onBtnClick() {
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Gets text from user input
                final String itemName = et1.getText().toString();
                final String quantity = et2.getText().toString();
                // Checks if itemName and quantity are empty
                if (noItemError(itemName, quantity))
                    Toast.makeText(ViewItemsActivity.this, "noItemError. Please input an item name and quantity.", Toast.LENGTH_LONG).show();

                    // Adds itemName and quantity to the list view
                else {
                    arrayList1.add("Item: " + itemName + ", " + "Quantity: " + quantity);
                    adapter1.notifyDataSetChanged();
                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... params) {

                            try {


                                // Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(ViewItemsActivity.this);
                                String url = "http://10.0.2.2:4000/items";
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {

                                                //textView.setText("Response is: " + response);
                                                //Toast.makeText(ViewItemsActivity.this, response, Toast.LENGTH_LONG).show();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //textView.setText("you suck at comp sci");
                                        //Toast.makeText(ViewItemsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        String list = getIntent().getStringExtra("ListViewListName");
                                        String email = getIntent().getStringExtra("LoggedInEmail");
                                        params.put("itemname", itemName);
                                        params.put("quantity ", quantity);
                                        params.put("creator", email);
                                        params.put("list", list);

                                        // params.put("email", email);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //textView.setText(ex.toString());
                                //Toast.makeText(ViewItemsActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
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
        // Gets the list name the user is viewing
        String list = getIntent().getStringExtra("ListViewListName");
        // Sends the list name to the AddUserActivity
        intent.putExtra("ListViewListName", list);
        String userEmail = getIntent().getStringExtra("LoggedInEmail");
        intent.putExtra("ListViewUserEmail", userEmail);

        startActivity(intent);
    }

    // Displays message if missing item field submission
    public boolean noItemError(String itemName, String itemQuantity) {
        boolean result = false;
        if (itemName.length() == 0 || itemQuantity.length() == 0) {
            result = true;
        }
        return result;
    }
}

