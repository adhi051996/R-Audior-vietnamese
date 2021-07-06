package com.rentokil.pci.rauditor_vn;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class PDF_MAIL extends AppCompatActivity {

    WebView webview;





    String key_id="",aud_name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__mail);






//        webview.getSettings().setJavaScriptEnabled(true);
//        String filename ="https://rauditor.riflows.com/Android/downloads/PCI/PCI_"+key_id+".pdf";
//
//        webview.getSettings().setLoadWithOverviewMode(true);
//
//        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);
//
//        webview.getSettings().setAllowFileAccessFromFileURLs(true);
//        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
//        webview.getSettings().setBuiltInZoomControls(true);
//
//        webview.setWebViewClient(new WebViewClient() {
//
//            public void onPageFinished(WebView view, String url) {
//                // do your stuff here
//
//            }
//        });
//


    }
}
