package com.homedev.MyHome.db;

import android.database.sqlite.SQLiteDatabase;
import com.homedev.MyHome.model.Address;
import com.homedev.MyHome.model.Street;

import static com.homedev.MyHome.model.Address.Addresses;
import static com.homedev.MyHome.model.Street.*;

public final class AddressDbHelper {

    public final static void insert(SQLiteDatabase db, Address address){
        if (address==null){
            return;
        }
        Long id =  db.insert(Addresses.TABLE_NAME, null, address.toContentValues());
        address.setId(id);
    }

    public static final void createAddressTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Addresses.TABLE_NAME + " (" +
                Addresses.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Addresses.STREET_ID + " INTEGER NOT NULL," +
                Addresses.HOUSE_NUMBER + " TEXT NOT NULL, " +
                Addresses.HOUSE_SUFFIX + " TEXT, "+
                Addresses.HOUSE_INDEX +" TEXT," +
                "FOREIGN KEY ("+ Addresses.STREET_ID+") REFERENCES "+ Streets.TABLE_NAME+"("+ Streets.ID+"))";
        db.execSQL(query);
    }

    public static final void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE "+Addresses.TABLE_NAME);
    }
}
