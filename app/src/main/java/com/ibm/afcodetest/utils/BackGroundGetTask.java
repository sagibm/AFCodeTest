package com.ibm.afcodetest.utils;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by sheldongabriel on 6/16/16.
 */
public class BackGroundGetTask extends AsyncTask<String,Void,String> {

    private String retVal;
    private ProgressDialog pDialog;
    private GetThreadDataListener getThread;
    private WebService caller;

    public BackGroundGetTask(WebService caller, GetThreadDataListener getListener) {

        this.caller = caller;
        getThread = getListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Showing progress dialog
        pDialog = new ProgressDialog(caller.context);

        pDialog.setMessage("Getting Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //pDialog.setCancelable(true);
        pDialog.show();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected String doInBackground(String... params) {

        retVal = caller.getRequest();

        return retVal;
    }

    @Override
    protected void onPostExecute(String feed) {

        if(feed != null) {
            pDialog.dismiss();
        }

        System.out.println(">>>>>> GET Method Data:  " + feed);
        getThread.setGetThreadData(feed);

    }
}
