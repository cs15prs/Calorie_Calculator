package com.caloriecalculator.calorie_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView mywebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mywebView=(WebView)findViewById(R.id.webview1);
        WebSettings WebSettings=mywebView.getSettings();
        WebSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("https://www.verywell.com/recipe-nutrition-analyzer-4129594");
        mywebView.setWebViewClient(new WebViewClient());
    }
}
