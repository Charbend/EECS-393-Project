package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    @SuppressLint("StaticFieldLeak")
    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads view items layout
        setContentView(R.layout.activity_view_items);

        // Sets local variables equal to layout objects
        et1 = findViewById(R.id.editText4);
        et2 = findViewById(R.id.editText6);
        bt1 = findViewById(R.id.button10);
        bt2 = findViewById(R.id.button12);
        lv1 = findViewById(R.id.listView_lv3);
        textView = findViewById(R.id.textView19);

        // Gets the list name the user clicked on from the view lists page
        String list = getIntent().getStringExtra("ListViewListName");
        // Sets the title to that list name
        textView.setText("List: " + list);

        // Creates array list to add entries to
        arrayList1 = new ArrayList<String>();
        // Creates adapter to add entries to list view
        adapter1 = new ArrayAdapter<String>(ViewItemsActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        // Sets the adapter to the list view to add text to
        lv1.setAdapter(adapter1);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(ViewItemsActivity.this);
                    // Sets url to items collection in database through server.js running locally
                    String url = "http://10.0.2.2:4000/items";
                    // GET request to get item entries the user already has stored in database
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {

                                    // Gets the logged in user email from view lists page
                                    String userEmail = getIntent().getStringExtra("LoggedInEmail");
                                    // Gets the list name from view lists page
                                    String listName = getIntent().getStringExtra("ListViewListName");

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

                                    // Creates array list used to store items
                                    ArrayList<String> correct = new ArrayList<>();

                                    // Cycles through resp
                                    for (int i = 0; i < resp.length; i++) {
                                        // If response contains the user's email
                                        if (resp[i].contains(userEmail) && resp[i].contains(listName)) {

                                            // Set s to string in front of itemName:
                                            String s = resp[i].split("itemname\":")[1];
                                            // Set s to string before comma
                                            s = s.split(",")[0];
                                            // Deletes slashes from s
                                            s = s.replace("\"", "");
                                            // Deletes curly bracket from s, left with the name of the item
                                            s = s.replace("}", "");

                                            // Set s2 to string in front of quantity:
                                            String s2 = resp[i].split("quantity \":")[1];
                                            // Set s2 to string before comma
                                            s2 = s2.split(",")[0];
                                            // Deletes slashes from s2
                                            s2 = s2.replace("\"", "");
                                            // Deletes curly bracket from s2, left with the quantity
                                            s2 = s2.replace("}", "");

                                            // Sets s3 equal to string that displays item and quantity
                                            String s3 = "Item: " + s + ", " + "Quantity: " + s2;

                                            // Adds s3 to correct array list
                                            correct.add(s3);
                                        }
                                    }

                                    // If anything is in correct array list
                                    if (correct.size() > 0) {
                                        // Loops though correct
                                        for (String str : correct) {
                                            // Add the strings to arrayList1
                                            arrayList1.add(str);
                                            // Adapter displays entries in list view
                                            adapter1.notifyDataSetChanged();
                                        }
                                    }
                                    // Else there were no items for this user in the database, so display message indicating so
                                    else {
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
        // Goes to onBtnClick method when user adds a new entry
        onBtnClick();
    }

    // Puts user entered data into list view once button is clicked
    public void onBtnClick() {
        bt1.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {

                // Gets user entered text from the edit text fields
                final String itemName = et1.getText().toString();
                final String quantity = et2.getText().toString();

                // Checks if item name and quantity are empty, and displays message if true
                if (noItemError(itemName, quantity))
                    Toast.makeText(ViewItemsActivity.this, "noItemError. Please input an item name and quantity.", Toast.LENGTH_LONG).show();
                // Adds item name and quantity to the list view
                else {
                    // Adds the text to the array list
                    arrayList1.add("Item: " + itemName + ", " + "Quantity: " + quantity);
                    // Adapter displays the text in list view
                    adapter1.notifyDataSetChanged();

                    new AsyncTask<Void, Void, String>() {

                        @Override
                        protected String doInBackground(Void... params) {

                            try {

                                // Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(ViewItemsActivity.this);
                                // Connects to items collection in database through server.js running locally
                                String url = "http://10.0.2.2:4000/items";
                                // POST request to items entries to the database
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {

                                                //Toast.makeText(ViewItemsActivity.this, response, Toast.LENGTH_LONG).show();

                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Toast.makeText(ViewItemsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    // Sends parameters to database to be added
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        // Gets list name from view lists page
                                        String list = getIntent().getStringExtra("ListViewListName");
                                        // Gets user email from view lists page
                                        String email = getIntent().getStringExtra("LoggedInEmail");
                                        // Adds the item name entered as the itemName to database
                                        params.put("itemname", itemName);
                                        // Adds the quantity entered as the quantity to database
                                        params.put("quantity ", quantity);
                                        // Adds user email as the creator to database
                                        params.put("creator", email);
                                        // Adds the list name entered as the list to database
                                        params.put("list", list);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //Toast.makeText(ViewItemsActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
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

    // Called when the user taps the ADD USER button
    public void addUser(View view) {
        // Creates intent to take user from the view items page to add users page
        Intent intent = new Intent(this, AddUserActivity.class);
        // Gets the list name the user is viewing
        String list = getIntent().getStringExtra("ListViewListName");
        // Sends the list name to the add users page
        intent.putExtra("ListViewListName", list);
        // Gets the logged in user email from view lists page
        String userEmail = getIntent().getStringExtra("LoggedInEmail");
        // Sends the logged in user email to the add users page
        intent.putExtra("ListViewUserEmail", userEmail);
        // Starts intent
        startActivity(intent);
    }

    // Displays message if missing item field submission
    public boolean noItemError(String itemName, String itemQuantity) {
        boolean result = false;
        // If no entry for item name, quantity, then return true that there is a noItemError
        if (itemName.length() == 0 || itemQuantity.length() == 0) {
            result = true;
        }
        return result;
    }
}

