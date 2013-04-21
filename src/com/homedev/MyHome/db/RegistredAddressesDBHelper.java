package com.homedev.MyHome.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.homedev.MyHome.R;
import com.homedev.MyHome.model.Address;
import com.homedev.MyHome.model.MessageType;
import com.homedev.MyHome.model.Street;

import java.util.ArrayList;
import java.util.List;

import static com.homedev.MyHome.model.Address.Addresses;
import static com.homedev.MyHome.model.MessageType.MessageTypes.*;
import static com.homedev.MyHome.model.Street.Streets;

public class RegistredAddressesDBHelper extends SQLiteOpenHelper{
    private Resources resources;

    private RegistredAddressesDBHelper instance;


    public RegistredAddressesDBHelper(Context context, String app_name, int dbVersion) {
        super(context,app_name, null, dbVersion);
        resources=context.getResources();
        this.instance=this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StreetDbHelper.createStreetsTable(db);
        addStreets(db);
         AddressDbHelper.createAddressTable(db);
        MessageTypeDbHelper.createMessageTypeTable(db);
        addMessageTypes(db);
        MessageDbHelper.createMessageTable(db);

    }


    private void addStreets(SQLiteDatabase db) {
        Street street =  addStreet(db, new Street("20-я линия"));
        addAddress(db, new Address(street, "3", null, "д"));
        street=addStreet(db, new Street("Жукова"));
        addAddress(db, new Address(street, "24", "2", null));
        street=addStreet(db, new Street("Кирова"));
        addAddress(db, new Address(street, "1", null, null));
        street=addStreet(db, new Street("Карла Маркса"));
        addAddress(db, new Address(street, "1", null, null));
        street=addStreet(db, new Street("10 лет Октября"));
        addAddress(db, new Address(street, "15", null, null));
        street=addStreet(db, new Street("Маяковского"));
        addAddress(db, new Address(street, "34", null, null));
        street= addStreet(db, new Street("проспект Мира"));
        addAddress(db, new Address(street, "38", null, null));
    }

    private void addAddress(SQLiteDatabase db, Address address) {
       long id=  db.insert(Addresses.TABLE_NAME, null, address.toContentValues());
        address.setId(id);
    }

    private Street addStreet(SQLiteDatabase db, Street street) {
        StreetDbHelper.insert(db, street);
        return street;
    }

    private void addMessageTypes(SQLiteDatabase db) {
        MessageTypeDbHelper.insert(db, resources.getString(R.string.hot));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.electricity));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.hotWater));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.coldWater));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.gas));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.announce));
    }

    public List<Address> findAddress(String text){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ Addresses.TABLE_NAME+","+Streets.TABLE_NAME+" WHERE " +
                Addresses.TABLE_NAME+"."+Addresses.STREET_ID+"="+Streets.TABLE_NAME+"."+Streets.ID+" " +
                "AND "+Streets.STREET_NAME+" LIKE %"+text+"%";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Address> result = new ArrayList<Address>();
        if (cursor.getCount()==0){
            return result;
        }
        cursor.moveToFirst();
        do {
            Address address = createAddressFormCursor(cursor);
            result.add(address);
        } while (cursor.moveToNext());
        return result;
    }

    private Address createAddressFormCursor(Cursor cursor) {
        String streetName = cursor.getString(cursor.getColumnIndex(Streets.STREET_NAME));
        Long streetId = cursor.getLong(cursor.getColumnIndex(Streets.ID));
        Street street = new Street(streetName);
        street.setId(streetId);
        String houseNumber = cursor.getString(cursor.getColumnIndex(Addresses.HOUSE_NUMBER));
        String index = cursor.getString(cursor.getColumnIndex(Addresses.HOUSE_INDEX));
        String suffix = cursor.getString(cursor.getColumnIndex(Addresses.HOUSE_SUFFIX));
        Address address = new Address(street, houseNumber,index,suffix);
        return address;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          MessageDbHelper.dropTable(db);
          MessageTypeDbHelper.dropTable(db);
          AddressDbHelper.dropTable(db);
          StreetDbHelper.dropTable(db);
          onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onUpgrade(db, oldVersion, newVersion);
    }

    public RegistredAddressesDBHelper getInstance() {
        return instance;
    }
}
