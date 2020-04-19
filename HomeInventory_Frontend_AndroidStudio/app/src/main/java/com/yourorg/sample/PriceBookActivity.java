package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class PriceBookActivity extends AppCompatActivity {

    // Local variables to represent layout objects
    EditText et1, et2, et3, et4, et5;
    Button bt;
    ListView lv1;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> adapter1;

    @SuppressLint("StaticFieldLeak")
    @Override
    // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads price book layout
        setContentView(R.layout.activity_price_book);

        // Sets local variables equal to actual layout objects
        et1 = findViewById(R.id.editText5);
        et2 = findViewById(R.id.editText7);
        et3 = findViewById(R.id.editText8);
        et4 = findViewById(R.id.editText11);
        et5 = findViewById(R.id.editText12);
        bt = findViewById(R.id.button11);
        lv1 = findViewById(R.id.listView_lv5);

        // Creates array list to add entries to
        arrayList1 = new ArrayList<String>();
        // Creates adapter to add entries to list view
        adapter1 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        // Sets the adapter to the list view to add text to
        lv1.setAdapter(adapter1);

        new AsyncTask<Void, Void, String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected String doInBackground(Void... params) {

                try {

                    //Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(PriceBookActivity.this);
                    // Sets url to pricebookitems collection in database through server.js running locally
                    String url = "http://10.0.2.2:4000/pricebookitems";
                    // GET request to get price book entries the user already has stored in database
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

                                    // Creates array list used to store price book items
                                    ArrayList<String> correct = new ArrayList<>();

                                    // Cycles through response
                                    for (int i = 0; i < resp.length; i++) {
                                        // If response contains the user's email
                                        if (resp[i].contains(userEmail)) {

                                            // Set s to string in front of itemName:
                                            String s = resp[i].split("itemName\":")[1];
                                            // Set s to string before comma
                                            s = s.split(",")[0];
                                            // Deletes slashes from s
                                            s = s.replace("\"", "");
                                            // Deletes curly bracket from s, left with the name of the item
                                            s = s.replace("}", "");

                                            // Set s2 to string in front of quantity:
                                            String s2 = resp[i].split("quantity\":")[1];
                                            // Set s2 to string before comma
                                            s2 = s2.split(",")[0];
                                            // Deletes slashes from s2
                                            s2 = s2.replace("\"", "");
                                            // Deletes curly bracket from s2, left with the quantity
                                            s2 = s2.replace("}", "");

                                            // Sets s3 equal to string that displays item and quantity, used later
                                            String s3 = "Item: " + s + ", " + "Quantity: " + s2;

                                            // Set s4 to string in front of price:
                                            String s4 = resp[i].split("price\":")[1];
                                            // Set s4 to string before comma
                                            s4 = s4.split(",")[0];
                                            // Deletes slashes from s4
                                            s4 = s4.replace("\"", "");
                                            // Deletes curly bracket from s4, left with the price
                                            s4 = s4.replace("}", "");

                                            // Set s5 to string in front of location:
                                            String s5 = resp[i].split("location\":")[1];
                                            // Set s5 to string before comma
                                            s5 = s5.split(",")[0];
                                            // Deletes slashes from s5
                                            s5 = s5.replace("\"", "");
                                            // Deletes curly bracket from s5, left with the location
                                            s5 = s5.replace("}", "");

                                            // Sets s6 equal to string that displays price and location, used later
                                            String s6 = "Price: " + s4 + ", " + "Location: " + s5;

                                            // Set s7 to string in front of category:
                                            String s7 = resp[i].split("category\":")[1];
                                            // Set s7 to string before comma
                                            s7 = s7.split(",")[0];
                                            // Deletes slashes from s7
                                            s7 = s7.replace("\"", "");
                                            // Deletes curly bracket from s7, left with the category
                                            s7 = s7.replace("}", "");

                                            // Sets s8 equal to string that displays category, used later
                                            String s8 = "Category: " + s7;

                                            // Sets string 9 to previous price book concatenations
                                            String s9 = s3 + ", " + s6 + ", " + s8;
                                            // Deletes closed bracket that sometimes appears
                                            s9 = s9.replace("]", "");

                                            // Add the price book entry to the correct array list
                                            correct.add(s9);

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
                                    // Else there were no price book items for this user in the database, so display message indicating so
                                    else {
                                        Toast.makeText(PriceBookActivity.this, "no pricebookitems for this user", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PriceBookActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                } catch (Exception ex) {
                    //Toast.makeText(PriceBookActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
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
        bt.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {

                // Gets text from edit text fields
                final String itemEntry = et1.getText().toString();
                final String quantityEntry = et2.getText().toString();
                final String priceEntry = et3.getText().toString();
                final String locationEntry = et4.getText().toString();
                final String categoryEntry = et5.getText().toString();

                // Checks for empty fields
                if (noPriceBookItemError(itemEntry, quantityEntry, priceEntry, locationEntry, categoryEntry))
                    Toast.makeText(PriceBookActivity.this, "noPriceBookItemError. Please fill all the fields ", Toast.LENGTH_LONG).show();
                else {
                    // Adds the text to the array list
                    arrayList1.add("Item: " + itemEntry + ", " + "Quantity: " + quantityEntry + ", " + "Price: " +
                            priceEntry + ", " + "Location Purchased: " + locationEntry + ", " + "Category: "
                            + categoryEntry);
                    // Adapter displays the text in list view
                    adapter1.notifyDataSetChanged();
                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... params) {

                            try {
                                //Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(PriceBookActivity.this);
                                // Connects to pricebookitems collection in database through server.js running locally
                                String url = "http://10.0.2.2:4000/pricebookitems";
                                // POST request to add price book entries to the database
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {
                                                //Toast.makeText(PriceBookActivity.this, response, Toast.LENGTH_LONG).show();
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(PriceBookActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    // Sends parameters to database to be added
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        // Gets user email from home page
                                        String userEmail = getIntent().getStringExtra("loggedInEmail");
                                        // Adds user email as the creator to database
                                        params.put("creator", userEmail);
                                        // Adds the item name entered as the itemName to database
                                        params.put("itemName", itemEntry);
                                        // Adds the quantity entered as the quantity to database
                                        params.put("quantity", quantityEntry);
                                        // Adds the price entered as the price to database
                                        params.put("price", priceEntry);
                                        // Adds the location entered as the location to database
                                        params.put("location", locationEntry);
                                        // Adds the category entered as the category to database
                                        params.put("category", categoryEntry);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //textView.setText(ex.toString());
                                Toast.makeText(PriceBookActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(String result) {
                            //textView.setText(result)
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

    // Displays message if missing price book field submission
    public boolean noPriceBookItemError(String itemName, String itemQuantity, String itemPrice, String locationPurch, String category) {
        boolean result = false;
        // If no entry for item name, quantity, price, location, and category then return true that there is a noPriceBookItemError
        if (itemName.length() == 0 || itemQuantity.length() == 0 || itemPrice.length() == 0
                || locationPurch.length() == 0 || category.length() == 0) {
            result = true;
        }
        return result;
    }
}
