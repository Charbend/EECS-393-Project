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
    public static boolean _startedNodeAlready=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( !_startedNodeAlready ) {
            _startedNodeAlready=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Part of my attempt to start node
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

        final Button buttonVersions = findViewById(R.id.btVersions);
        final TextView textViewVersions = findViewById(R.id.tvVersions);

        buttonVersions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Network operations should be done in the background.
                new AsyncTask<Void,Void,String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        String nodeResponse="";
                        try {

                            // Address that connects emulator with your localhost. Enter this into emulator browser.
                            URL localNodeServer = new URL("http://10.0.2.2:4000/users");

                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(localNodeServer.openStream()));
                            String inputLine;
                            while ((inputLine = in.readLine()) != null)
                                nodeResponse=nodeResponse+inputLine;
                            in.close();
                        } catch (Exception ex) {
                            nodeResponse=ex.toString();
                        }
                        return nodeResponse;
                    }
                    @Override
                    protected void onPostExecute(String result) {
                        if(result.contains("test@test.com"))
                            textViewVersions.setText("Connected to server");
                        else
                            textViewVersions.setText("Not connected to server");
                    }
                }.execute();

            }
        });

    }
    

    // Called when the user taps the LOG-IN button, transfers them to LogInActivity2
    public void logIn(View view) {
        Intent intent = new Intent(this, LogInActivity2.class);
        startActivity(intent);
    }

     

    // Called when the user taps the SIGN UP button, transfers them to SignUpActivity
    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native Integer startNodeWithArguments(String[] arguments);
}
