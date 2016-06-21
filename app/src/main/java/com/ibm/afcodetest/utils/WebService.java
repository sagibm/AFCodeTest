package com.ibm.afcodetest.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sheldongabriel on 6/16/16.
 */
public class WebService {

    private HttpURLConnection connection = null;
    private URL serverAddress = null;
    private OutputStream os = null;
    private InputStream is = null;
    private BufferedReader rd  = null;
    public Context context;
    private String url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";

    public WebService(Context context) {

        this.context = context;
    }

    public void performGet(GetThreadDataListener listener) {

        new BackGroundGetTask(this, listener).execute("");
    }

    public String getRequest() {

        StringBuffer response = new StringBuffer();

        System.out.println("Calling GET:  " + url);

        try {

            URL serverAddress = new URL(url);
            connection = (HttpURLConnection)serverAddress.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_INTERNAL_ERROR) {

                throw new IOException("Unexpected HTTP response: "+ connection.getResponseCode() + " " + connection.getResponseMessage());
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();

        }
        catch(Exception ex) {
        }

        return response.toString();
    }



}
