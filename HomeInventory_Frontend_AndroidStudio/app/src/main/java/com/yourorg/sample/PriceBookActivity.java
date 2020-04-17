package com.yourorg.sample;

import androidx.appcompat.app.AppCompatActivity;

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
    //public static String itemEntered, qtyEntered, priceEntered, locationEntered, categoryEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_book);

        // Sets local variables equal to layout objects
        et1 = findViewById(R.id.editText5);
        et2 = findViewById(R.id.editText7);
        et3 = findViewById(R.id.editText8);
        et4 = findViewById(R.id.editText11);
        et5 = findViewById(R.id.editText12);
        bt = findViewById(R.id.button11);
        lv1 = findViewById(R.id.listView_lv5);

        final String itemEntered = et1.toString();
        final String qtyEntered = et2.toString();
        final String priceEntered = et3.toString();
        final String locationEntered = et4.toString();
        final String categoryEntered = et5.toString();
        //final boolean result = true;

        // Sets the list view to add text to
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    //Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(PriceBookActivity.this);

                    String url = "http://10.0.2.2:4000/pricebookitems";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    String userEmail = getIntent().getStringExtra("loggedInEmail");
                                    if (response.contains(itemEntered) && response.contains(qtyEntered) && response.contains(priceEntered) && response.contains(locationEntered) && response.contains(categoryEntered)) {
                                        //result = false;
                                        Toast.makeText(PriceBookActivity.this, "item already exists", Toast.LENGTH_LONG).show();
                                    }

                                    // Splits response by commas
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
                                        if (resp[i].contains(userEmail)) {

                                            String s = resp[i].split("itemName\":")[1];
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
                                        Toast.makeText(PriceBookActivity.this, "no pricebookitems for this user", Toast.LENGTH_LONG).show();
                                    }
                                }
                                }, new Response.ErrorListener()

                                {
                                    @Override
                                    public void onErrorResponse (VolleyError error){
                                    Toast.makeText(PriceBookActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                                });
                    queue.add(stringRequest);
                            } catch(Exception ex){
                        Toast.makeText(PriceBookActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                    }

                    return null;
                }

                @Override
                protected void onPostExecute (String result){
                }
            }.execute();



        onBtnClick();

    }

    // Puts user entered data into list view once button is clicked
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

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

                    // Checks for invalid price entry, NEED TO GET WORKING
                /*else if (Double.parseDouble(et3.getText().toString()) == 0) {
                    (et1.getClass().getSimpleName().equals(int))
                    invalidPriceBookItemError();
                } */
                else {
                    // MIGHT HAVE TO CHANGE THIS FOR MONGODB
                    // Adds the text to the list view
                    arrayList1.add("Item: " + itemEntry + ", " + "Quantity: " + quantityEntry + ", " + "Price: " +
                            priceEntry + ", " + "Location Purchased: " + locationEntry + ", " + "Category: "
                            + categoryEntry);
                    adapter1.notifyDataSetChanged();
                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... params) {

                            try {
                                //Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(PriceBookActivity.this);
                                String url = "http://10.0.2.2:4000/pricebookitems";
                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {
                                                String userEmail = getIntent().getStringExtra("loggedInEmail");
                                                //textView.setText("Response is: " + response);
                                                Toast.makeText(PriceBookActivity.this, response, Toast.LENGTH_LONG).show();
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //textView.setText("you suck at comp sci");
                                        Toast.makeText(PriceBookActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        String userEmail = getIntent().getStringExtra("loggedInEmail");
                                        params.put("creator", userEmail);
                                        params.put("itemName", itemEntry);
                                        params.put("quantity", quantityEntry);
                                        params.put("price", priceEntry);
                                        params.put("location", locationEntry);
                                        params.put("category", categoryEntry);

                                        return params;
                                    }
                                };
                                queue.add(postRequest);

                            } catch (Exception ex) {
                                //textView.setText(ex.toString());
                                Toast.makeText(PriceBookActivity.this, ex.toString(), Toast.LENGTH_LONG).show();
                            }

                            //return textView.getText().toString();
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
        if (itemName.length() == 0 || itemQuantity.length() == 0 || itemPrice.length() == 0
                || locationPurch.length() == 0 || category.length() == 0) {
            result = true;
        }
        return result;
    }

    // Displays message if missing price book field submission
    public void invalidPriceBookItemError() {
        Toast.makeText(PriceBookActivity.this, "invalidPriceBookItemError. Please enter a string or int for item name," +
                " integer for quantity, double for cost, string for location purchased," +
                " and string for category.", Toast.LENGTH_LONG).show();
    }
}
