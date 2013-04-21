package com.homedev.MyHome.model;

import android.content.ContentValues;

public class TextAddress extends DomainEntry<Long> {

    private String address;

    public TextAddress(String address) {
        this.address = address;
    }

    public TextAddress(Long id, String address) {
        setId(id);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues result = new ContentValues();
        if (getId()!=null){
             result.put("id", getId());
        }
        result.put("address",address);
        return result;
    }
}
