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
import com.homedev.MyHome.db.RegistredAddressesDBHelper;
import com.homedev.MyHome.model.Address;
import com.homedev.MyHome.model.TextAddress;
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

    private Button okButton;
    private AutoCompleteTextView autoCompleteTextView;
    private EditText number;
    private EditText sector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_register_layout);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.address_finder);
        autoCompleteTextView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.address_item_layout));
        autoCompleteTextView.setOnItemClickListener(this);
        okButton = (Button) findViewById(R.id.ok);
        sector=(EditText) findViewById(R.id.sector);
        number = (EditText) findViewById(R.id.number);
        number.addTextChangedListener(new TextChangeListener());
        autoCompleteTextView.addTextChangedListener(new TextChangeListener());
        okButton.setEnabled(false);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = autoCompleteTextView.getText().toString();
                String houseNumber = number.getText().toString();
                String houseSector = sector.getText().toString();
                String addressString = address+";"+houseNumber+";"+houseSector;
                 TextAddress textAddress = new TextAddress(addressString);
                 RegistredAddressesDBHelper.getInstance().insert(textAddress);
                finish();
            }
        });

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private class TextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void afterTextChanged(Editable s) {
           okButton.setEnabled(!StringUtils.isNullOrEmpty(autoCompleteTextView.getText().toString())
                            && !StringUtils.isNullOrEmpty(number.getText().toString()));
        }
    }
}
