package com.homedev.MyHome.util;

import android.util.Log;
import com.homedev.MyHome.HomeApp;
import com.homedev.MyHome.shared.ApplicationProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AutoCompleteUtils {

    private static final String LOG_TAG = "HomeDevApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";

    private static final String OUT_JSON ="/json";


    public final static ArrayList<String> autoComplete (String input){
        ArrayList<String> resultList = null;

        Log.d(LOG_TAG, "autoComplete");
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();

        try{
            StringBuilder sb = new StringBuilder(PLACES_API_BASE+TYPE_AUTOCOMPLETE+OUT_JSON);
            sb.append("?sensor=false&key=" + ApplicationProperties.getApiKey());
            sb.append("&components=country:ru");
            sb.append("&input=Omsk,%20"+ URLEncoder.encode(input, "utf8"));
            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff= new char[1024];
            while ((read = in.read(buff)) != -1){
                jsonResults.append(buff, 0, read);
            }
        }   catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        }  catch (IOException e){
            Log.e(LOG_TAG, "Error connecting to Places API");
            return resultList;
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObject.getJSONArray("predictions");

            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i=0; i<predsJsonArray.length(); i++){
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e){
            Log.e(LOG_TAG, "Cannot process JSON results",e);
        }
        Log.d(LOG_TAG, "result: " + resultList.toString());
        return resultList;
    }
}