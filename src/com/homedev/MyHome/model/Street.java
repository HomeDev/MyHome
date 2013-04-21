package com.homedev.MyHome.model;


import android.content.ContentValues;

import static com.homedev.MyHome.model.Street.Streets.ID;

public class Street extends DomainEntry<Long> {
    private String name;

    public Street(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues result = new ContentValues();
        if (getId()!=null){
            result.put(ID, getId());
        }
        result.put(Streets.STREET_NAME, getName());
        return result;
    }

    public static final class Streets {
        private Streets() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="street";
        public final static String ID = "street_id";
        public final static String STREET_NAME = "street_name";
    }
}
