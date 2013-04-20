package com.homedev.MyHome;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.homedev.MyHome.model.Address;
import com.homedev.MyHome.util.StringUtils;
import com.homedev.MyHome.adapter.AddressListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeListActivity extends Activity {
    private Button okButton;
    private Button cancelButton;
    private EditText streetEdit;
    private EditText homeNumberEdit;
    private EditText homeIndexEdit;
    private List<Address> addressList;
    private ArrayAdapter<Address> addressArrayAdapter;
    private ListView addressListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_register_layout);
        okButton=(Button) findViewById(R.id.home_ok);
        cancelButton = (Button) findViewById(R.id.home_cancel);
        streetEdit=(EditText)findViewById(R.id.street);
        homeNumberEdit=(EditText)findViewById(R.id.home_number);
        homeIndexEdit=(EditText)findViewById(R.id.home_index);
        okButton.setEnabled(false);
        addressListView=(ListView) findViewById(R.id.home_list);
        streetEdit.addTextChangedListener(new EnterHomeListener());
        okButton.setOnClickListener(new OnOkClickListener());
        addressList=new ArrayList<Address>();
        addressArrayAdapter = new AddressListAdapter(this, R.layout.address_item_layout, addressList);


    }

    private class EnterHomeListener implements TextWatcher{

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
            okButton.setEnabled(!StringUtils.isNullOrEmpty(streetEdit.getText().toString())
                    &&!StringUtils.isNullOrEmpty(homeNumberEdit.getText().toString()));
        }
    }

    private class OnOkClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Address address=new Address(streetEdit.getText().toString()
                    ,homeNumberEdit.getText().toString()
                    ,homeIndexEdit.getText().toString());
            addressList.add(address);
            addressArrayAdapter.notifyDataSetChanged();
        }
    }
}
