package com.ibm.afcodetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sheldongabriel on 6/20/16.
 */
public class MyWebView extends Activity {
    private WebView webView;
    public static String WEB_TARGET = "web_target";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        String url = this.getIntent().getStringExtra(WEB_TARGET);

        webView = (WebView)this.findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
