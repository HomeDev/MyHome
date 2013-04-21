package com.homedev.MyHome.model;

import android.content.ContentValues;

import static com.homedev.MyHome.model.Address.Addresses.*;

public class Address  extends DomainEntry<Long>{
    private Street street;
    private String houseNumber;
    private String index;
    private String suffix;


    public Address(Street street, String houseNumber, String index, String suffix) {
        this.street= street;
        this.houseNumber = houseNumber;
        this.index = index;
        this.suffix=suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public Street getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (houseNumber != null ? !houseNumber.equals(address.houseNumber) : address.houseNumber != null) return false;
        if (index != null ? !index.equals(address.index) : address.index != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", index='" + index + '\'' +
                '}';
    }

    public String toUiString(){
          return new StringBuilder("").append(street).append(",").append(houseNumber).append("/").append(index).toString();
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        if (getId()!=null){
             contentValues.put(ID, getId());
        }
        contentValues.put(HOUSE_NUMBER, getHouseNumber());
        contentValues.put(HOUSE_INDEX, getIndex());
        contentValues.put(HOUSE_SUFFIX, getSuffix());
        contentValues.put(STREET_ID, getStreet().getId());
        return contentValues;
    }

    public static final class Addresses {
        private Addresses() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="address";
        public final static String ID = "address_id";
        public final static String STREET_ID = "street_id";
        public final static String HOUSE_NUMBER = "house_number";
        public final static String HOUSE_INDEX = "house_index";
        public final static String HOUSE_SUFFIX = "house_suffix";
    }

}
