package com.ibm.afcodetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ibm.afcodetest.model.AFData;
import com.ibm.afcodetest.model.DBHandler;
import com.ibm.afcodetest.utils.GetThreadDataListener;
import com.ibm.afcodetest.utils.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AFMainActivity extends Activity implements GetThreadDataListener,AdapterView.OnItemClickListener {

    private WebService ws;
    private AFListAdapter adapter;
    private ArrayList<AFData> afData;
    private DBHandler dbHandler;
    private ListView listView;
    public static String PRODUCT_CARD = "product";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afmain);

        listView = (ListView) this.findViewById(R.id.listview);
        dbHandler = new DBHandler(this);

        ws = new WebService(this);
        ws.performGet(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_afmain, menu);
        return true;
    }


    @Override
    public void setGetThreadData(String val) {

        if (val != null) {

            this.parseJSON(val);
            afData = dbHandler.getAllAFData();
            adapter = new AFListAdapter(this,afData);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
        else {

            System.out.println("************* NO JSOM FOUND ***********");
        }
    }

    public void parseJSON(String data) {

        try {

            System.out.println("PARSE JSON DATA: " + data);

            JSONArray afArry = new JSONArray(data);

            String promMsg = null;
            String bottomDesc = null;
            String topDesc = null;

            for (int i = 0; i < afArry.length(); i++) {

                JSONObject afObj = afArry.getJSONObject(i);

                System.out.println("AF OBJECTS: " + afArry.getJSONObject(i));
                String bgUrls = afObj.getString("backgroundImage");

                if (afObj.has("topDescription")) {
                    topDesc = afObj.getString("topDescription");
                }

                String title = afObj.getString("title");

                if (afObj.has("promoMessage")) {
                    promMsg = afObj.getString("promoMessage");
                }

                if (afObj.has("bottomDescription")) {
                    bottomDesc = afObj.getString("bottomDescription");
                }

                AFData clothes = new AFData();
                clothes.setBackGroundUrl(bgUrls);
                clothes.setTopDesc(topDesc);
                clothes.setTitle(title);
                clothes.setPromoMsg(promMsg);
                clothes.setBottomDesc(bottomDesc);

                if(afObj.has("content")) {

                    JSONArray contentArry = afObj.getJSONArray("content");

                    JSONObject contents = new JSONObject();
                    contents.put("content",afObj.getJSONArray("content"));
                    clothes.setContentArray(contents.toString());
                }

                dbHandler.addAFData(clothes);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       AFData selected = adapter.getItem(position);
        Bundle bundle = new Bundle();

        System.out.println("BGURL: " + selected.getBackGroundUrl());
        System.out.println("TOP DESC: " + selected.getTopDesc());
        System.out.println("TITLE: " + selected.getTitle());
        System.out.println("PROMO: " + selected.getPromoMsg());
        System.out.println("BOTTOM DESC " + selected.getBottomDesc());
        System.out.println("CONTENT " + selected.getContentArray());


        Intent intent = new Intent(this,ProductCard.class);
        intent.putExtra(DBHandler.KEY_BACKROUND_URL,selected.getBackGroundUrl());
        intent.putExtra(DBHandler.KEY_TOP_DESC,selected.getTopDesc());
        intent.putExtra(DBHandler.KEY_TITLE,selected.getTitle());
        intent.putExtra(DBHandler.KEY_PROMO,selected.getPromoMsg());
        intent.putExtra(DBHandler.KEY_BOTTOM_DESC,selected.getBottomDesc());
        intent.putExtra(DBHandler.KEY_CONTENT_ARRAY,selected.getContentArray());

        this.startActivity(intent);
    }
}
