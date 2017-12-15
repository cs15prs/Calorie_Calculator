package com.caloriecalculator.calorie_calculator;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class HttpUtilityTester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_http_utility_tester);
        // test sending GET request
        String requestURL = "http://www.google.com";
        try {
            HttpUtility.sendGetRequest(requestURL);
            String[] response = HttpUtility.readMultipleLinesRespone();
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        HttpUtility.disconnect();


        System.out.println("=====================================");

        // test sending POST request
        Map<String, String> params = new HashMap<String, String>();
        requestURL = "https://accounts.google.com/ServiceLoginAuth";
        params.put("Email", "your_email");
        params.put("Passwd", "your_password");

        try {
            HttpUtility.sendPostRequest(requestURL, params);
            String[] response = HttpUtility.readMultipleLinesRespone();
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        HttpUtility.disconnect();
    }

    }




