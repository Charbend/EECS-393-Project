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
        itemEntered = et1;
        qtyEntered = et2;
        priceEntered = et3;
        locationEntered = et4;
        categoryEntered = et5;
        boolean result = true;

        // Sets the list view to add text to
        arrayList1 = new ArrayList<String>();
        adapter1 = new ArrayAdapter<String>(PriceBookActivity.this, android.R.layout.simple_list_item_1,
                arrayList1);
        lv1.setAdapter(adapter1);
        new Async<void,void,String>() {
            @Override
            protectd String doInBackground(Void... params) {
                
                try {
                    
                    //instatiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(PriceBookActivity.this);
                    string url = "http://10.0.2.2:4000/pricebookitems";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    
                                    @Override 
                                    public void onResponse(String response) {
                                        if (response.contains(itemEntered) && response.contains(qtyEntered) && response.contains(priceEntered) && response.contains(locationEntered) && response.contains(categoryEntered)) {
                                            result = false;
                                            Toast.makeText(PriceBookActivity.this, "item already exists", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(PriceBookActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
                
                return null;
            }
            @Override
            protected void onPostExecute(String result){
            }
        }.execute()
    }
                                            
                                        
                                        

        onBtnClick();

    }
    // Puts user entered data into list view once button is clicked
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Gets text from edit text fields
                String itemEntry = et1.getText().toString();
                String quantityEntry = et2.getText().toString();
                String priceEntry = et3.getText().toString();
                String locationEntry = et4.getText().toString();
                String categoryEntry = et5.getText().toString();

                // Checks for empty fields
                if (noPriceBookItemError(itemEntry, quantityEntry, priceEntry, locationEntry, categoryEntry))
                Toast.makeText(PriceBookActivity.this, "noPriceBookItemError. Please fill all the fields ", Toast.LENGTH_LONG).show();

                // Checks for invalid price entry, NEED TO GET WORKING
                /*else if (Double.parseDouble(et3.getText().toString()) == 0) {
                    (et1.getClass().getSimpleName().equals(int))
                    invalidPriceBookItemError();
                } */
                else if (result) {
                    // MIGHT HAVE TO CHANGE THIS FOR MONGODB
                    // Adds the text to the list view
                    arrayList1.add("Item: " + itemEntry + ", " + "Quantity: " + quantityEntry + ", " + "Price: " +
                            priceEntry + ", " + "Location Purchased: " + locationEntry + ", " + "Category: "
                            + categoryEntry);
                    adapter1.notifyDataSetChanged();
                    new AsyncTask<void, void, String> () {
                        @Override
                        protected String doInBackground(Void... params) {
                            try {
                                //Instantiate the RequestQueue.
                                RequestQueue queue = Volley.newRequestQueue(PriceBookActivity.this);
                                string url = "http://10.0.2.2:4000/pricebookitems";
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                            new Response.Listener<String>() {
                                                
                                                @Override
                                                Public void onResponse(String response) { 
                                                    
                                                    //textView.setText("Response is: " + response);
                                                    Toast.makeText(PriceBookActivity.this, response, Toast.LENGTH_LONG).show();
                                                }
                                            }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //textView.setText("you suck at comp sci");
                                        Toast.makeText(PriceBookActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                       }
                                 }){
                                       @Override
                                       protected Map<String, String> getParams()
                                       {
                                           Map<String,String> params = new HashMap<String, String>();
                                           params.put("Item", itemEntered);
                                           params.put("Qty", qtyEntered);
                                           params.put("Price", priceEntered);
                                           params.put("Location", locationEntered);
                                           params.put("Category", categoryEntered);
                                     
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
                    } catch (InterrupedException e) {
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
