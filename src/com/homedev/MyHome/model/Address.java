package com.homedev.MyHome.model;

public class Address {
    private String street;
    private String houseNumber;
    private String index;

    public Address(String street, String houseNumber, String index) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.index = index;
    }

    public String getStreet() {
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
}
