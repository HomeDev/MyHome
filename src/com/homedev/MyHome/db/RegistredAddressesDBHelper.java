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
import com.homedev.MyHome.model.TextAddress;

import java.util.ArrayList;
import java.util.List;

import static com.homedev.MyHome.model.Address.Addresses;
import static com.homedev.MyHome.model.MessageType.MessageTypes.*;
import static com.homedev.MyHome.model.Street.Streets;
import static com.homedev.MyHome.model.Street.Streets.ID;
import static com.homedev.MyHome.model.Street.Streets.STREET_NAME;
import static com.homedev.MyHome.model.Street.Streets.TABLE_NAME;

public class RegistredAddressesDBHelper extends SQLiteOpenHelper{
    private Resources resources;

    private static RegistredAddressesDBHelper instance;


    public RegistredAddressesDBHelper(Context context, String app_name, int dbVersion) {
        super(context,app_name, null, dbVersion);
        resources=context.getResources();
        this.instance=this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE text_address (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "address TEXT NOT NULL)";
        db.execSQL(query);
    }


    private void addStreets(SQLiteDatabase db) {
        db.beginTransaction();
        Street street =  addStreet(db, new Street("20-я линия"));
        addAddress(db, new Address(street, "3", "3", "д"));
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
        db.endTransaction();
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
                "AND "+Streets.STREET_NAME+"='%"+text+"%'";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Address> result = new ArrayList<Address>();
        if (cursor.getCount()<=0){
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
          db.execSQL("DROP TABLE text_address");
          onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onUpgrade(db, oldVersion, newVersion);
    }

    public static RegistredAddressesDBHelper getInstance() {
        return instance;
    }

    public void insert(TextAddress textAddress) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert("text_address", null, textAddress.toContentValues());
        textAddress.setId(id);
    }

    public List<TextAddress> findAllAddresses() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, address FROM text_address", null);
        List<TextAddress> result= new ArrayList<TextAddress>();
        if (!cursor.moveToFirst()){
            return result;
        }
        do {
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            result.add(new TextAddress(id, address));
        } while (cursor.moveToNext());
        return result;
    }
}
