package com.ibm.afcodetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibm.afcodetest.model.DBHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sheldongabriel on 6/17/16.
 */
public class ProductCard extends Activity {

    private ImageView imageView;
    private TextView topDesc;
    private TextView titleView;
    private TextView promoView;
    private TextView bottomDescView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_card_layout);

        String url = this.getIntent().getStringExtra(DBHandler.KEY_BACKROUND_URL);
        String topDescript = this.getIntent().getStringExtra(DBHandler.KEY_TOP_DESC);
        String title = this.getIntent().getStringExtra(DBHandler.KEY_TITLE);
        String promoDesc = this.getIntent().getStringExtra(DBHandler.KEY_PROMO);
        String bottomDesc = this.getIntent().getStringExtra(DBHandler.KEY_BOTTOM_DESC);

        imageView = (ImageView) this.findViewById(R.id.product_image);
        topDesc = (TextView) this.findViewById(R.id.topdescription);
        titleView = (TextView) this.findViewById(R.id.title);
        promoView = (TextView) this.findViewById(R.id.promo);

        bottomDescView = (TextView)this.findViewById(R.id.bottomdexcription);
        bottomDescView.setClickable(true);
        bottomDescView.setMovementMethod(LinkMovementMethod.getInstance());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        System.out.println("PRODUCT CARD: " + url);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imageView);

        topDesc.setText(topDescript);
        titleView.setText(title);
        promoView.setText(promoDesc);
        bottomDescView.setText(Html.fromHtml(bottomDesc));

        String contentJSON = this.getIntent().getStringExtra(DBHandler.KEY_CONTENT_ARRAY);

        if (contentJSON != null) {
            this.processButtons();
        }

    }


    private void processButtons() {

        String contentJSON = this.getIntent().getStringExtra(DBHandler.KEY_CONTENT_ARRAY);
        LinearLayout parentView = (LinearLayout) this.findViewById(R.id.product_layout);

        try {

            JSONObject contentObj = new JSONObject(contentJSON);
            JSONArray contentArry = contentObj.getJSONArray("content");

            for ( int j = 0; j < contentArry.length(); j++) {
                System.out.println("ARRAY CONTENT: " + contentArry.getJSONObject(j));
                final JSONObject jsonObject = contentArry.getJSONObject(j);

                LinearLayout buttonLL = (LinearLayout) View.inflate(this, R.layout.button_layout, null);
                Button button = (Button) buttonLL.findViewById(R.id.contentButton);
                button.setText(jsonObject.getString("title"));

                final int finalJ = j;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            System.out.println("URL SELECTECD: "+jsonObject.getString("target"));
                            Intent intent = new Intent(ProductCard.this,MyWebView.class);
                            intent.putExtra(MyWebView.WEB_TARGET,jsonObject.getString("target"));
                            ProductCard.this.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                if(button.getParent() != null)
                ((ViewGroup)button.getParent()).removeView(button);

                parentView.addView(button);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
