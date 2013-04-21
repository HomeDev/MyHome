package com.homedev.MyHome.util;

import android.util.Log;
import com.homedev.MyHome.db.RegistredAddressesDBHelper;
import com.homedev.MyHome.model.Address;
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
import java.util.List;

public class AutoCompleteUtils {

    private static final String LOG_TAG = "HomeDevApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";

    private static final String OUT_JSON ="/json";


    public final static List<Address> autoComplete (String input){
        List<Address> resultList = RegistredAddressesDBHelper.getInstance().findAddress(input);
        return resultList;
    }


}
