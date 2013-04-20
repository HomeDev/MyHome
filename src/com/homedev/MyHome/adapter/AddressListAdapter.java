package com.homedev.MyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.homedev.MyHome.R;
import com.homedev.MyHome.model.Address;
import org.w3c.dom.Text;


import java.util.ArrayList;
import java.util.List;

public class AddressListAdapter extends ArrayAdapter<Address> {
    private List<Address> addressList;

    public AddressListAdapter(Context context, int textViewResourceId, List<Address> objects) {
        super(context, textViewResourceId, objects);
        this.addressList=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View v = convertView;
         if (v==null){
             LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             v = inflater.inflate(R.layout.address_item_layout, null);
         }
        Address address = addressList.get(position);
        if (address!=null){
            TextView textView = (TextView) v.findViewById(R.id.address_as_string);
            String text =  address.toUiString();
            textView.setText(text);
        }
        return v;
    }
}
