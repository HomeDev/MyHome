package com.homedev.MyHome.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import com.homedev.MyHome.util.AutoCompleteUtils;

import java.util.ArrayList;

public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
    private ArrayList<String> resultList = new ArrayList<String>();

    public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    public String getItem(int index){
        String result = resultList.get(index);
        String[] data =  result.split(",");
        return data[data.length-1];
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint!=null){
                    resultList = AutoCompleteUtils.autoComplete(constraint.toString());

                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results!=null && results.count>0){
                    notifyDataSetChanged();
                }   else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}