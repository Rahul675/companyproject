package com.example.companyproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview_activity extends AppCompatActivity {

    WebView webView;
    String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_webview);
        Intent i = getIntent();
        score = i.getStringExtra("score");
//        Toast.makeText(this, "Score" + score, Toast.LENGTH_SHORT).show();
        webView = findViewById(R.id.webview);
        webView.loadUrl(score);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}