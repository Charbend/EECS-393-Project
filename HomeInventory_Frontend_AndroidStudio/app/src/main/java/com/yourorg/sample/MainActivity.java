package com.yourorg.sample;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.*;
import java.io.*;
import java.util.Map;

import android.content.Intent;

import javax.net.ssl.SSLContext;


public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("node");
    }

    //We just want one instance of node running in the background.
    public static boolean _startedNodeAlready = false;

    @Override
   // On screen creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads main layout
        setContentView(R.layout.activity_main);

        if (!_startedNodeAlready) {
            _startedNodeAlready = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Attempt to start node from within the app, doesn't work
                    /*try {
                        ProcessBuilder processBuilder = new ProcessBuilder("node server.js");
                        processBuilder = processBuilder.directory(new File("PATH TO server.js"));
                        Process process = processBuilder.start();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    // Original code for starting a node server, but doesn't work
                    /*startNodeWithArguments(new String[]{"node", "-e",
                            "var http = require('https'); " +
                                    "var versions_server = https.createServer( (request, response) => { " +
                                    "  response.end('Versions: ' + JSON.stringify(process.versions)); " +
                                    "}); " +
                                    "versions_server.listen(4000);"
                    });*/
                }
            }).start();
        }

        // Initializes variables to node versions button and textview in layout
        final Button buttonVersions = findViewById(R.id.btVersions);
        final TextView textViewVersions = findViewById(R.id.tvVersions);

        // Listener for when node versions button is clicked
        buttonVersions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Connects to node, and returns message in text view indicating success/failure
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        String nodeResponse = "";
                        try {

                            // Address that connects emulator with your localhost through server.js running locally
                            URL localNodeServer = new URL("http://10.0.2.2:4000/users");

                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(localNodeServer.openStream()));
                            String inputLine;
                            // Gets response from server
                            while ((inputLine = in.readLine()) != null)
                                nodeResponse = nodeResponse + inputLine;
                            in.close();
                        } catch (Exception ex) {
                            nodeResponse = ex.toString();
                        }
                        return nodeResponse;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        // If connected, the server returns the whole database as string
                        // If returned string contains test@test.com (our testing account), must be connected to server
                        if (result.contains("test@test.com"))
                            textViewVersions.setText("Connected to server");
                            // Else it must not be connected
                        else
                            textViewVersions.setText("Not connected to server");
                    }
                }.execute();

            }
        });

    }


    // Called when the user taps the LOG-IN button
    public void logIn(View view) {
        // Sets intent to go take from main to login screen
        Intent intent = new Intent(this, LogInActivity2.class);
        // Starts intent
        startActivity(intent);
    }


    // Called when the user taps the SIGN UP button, transfers them to SignUpActivity
    public void signUp(View view) {
        // Sets intent to go take user from main to sign up screen
        Intent intent = new Intent(this, SignUpActivity.class);
        // Starts intent
        startActivity(intent);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native Integer startNodeWithArguments(String[] arguments);
}
