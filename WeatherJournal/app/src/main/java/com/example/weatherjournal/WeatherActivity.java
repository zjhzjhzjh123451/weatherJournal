package com.example.weatherjournal;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class WeatherActivity extends AppCompatActivity {
    final String TAG = "tag";
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);
        tv = findViewById(R.id.temperature);
        tv.setText("adsfasdf");

    }

    private void getWeather() {
        StringBuffer document = new StringBuffer();
        int i;
        URL url;
        URLConnection conn;
        InputStream in;
        InputStreamReader isr;
        BufferedReader br;
        String line, str, string;
        StringBuffer sb;
        JSONObject jsonObject, JO;
        JSONArray jsonArray;

        try {
            String path = "https://wuliang.art/ncov/statistics/getProvinceHistoryList?provinceName=%E5%8F%B0%E6%B9%BE";

            url = new URL(path);
            Log.d(TAG,"0");

            conn = url.openConnection();
            Log.d(TAG,"0");

            in = conn.getInputStream();
            Log.d(TAG,"0");

            isr = new InputStreamReader(in, "UTF-8");
            Log.d(TAG,"0");
           
            br = new BufferedReader(isr);
            Log.d(TAG,"0");
            line = null;
            sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Log.d(TAG,"0");
            str = sb.toString();
            Log.d(TAG,"0");
        } catch (Exception e) {
            Log.d(TAG,e.toString());
        }
        tv.setText(document.toString());
    }
}
