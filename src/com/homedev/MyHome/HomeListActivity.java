package com.homedev.MyHome;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.homedev.MyHome.adapter.PlacesAutoCompleteAdapter;
import com.homedev.MyHome.model.Address;
import com.homedev.MyHome.util.StringUtils;
import com.homedev.MyHome.adapter.AddressListAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

public class HomeListActivity extends Activity implements AdapterView.OnItemClickListener {
    private List<Address> addressList;
    private ArrayAdapter<Address> addressArrayAdapter;
    private ListView addressListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_register_layout);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.address_finder);
        autoCompleteTextView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.address_item_layout));
        autoCompleteTextView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }



}
