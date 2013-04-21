package com.homedev.MyHome.db;

import android.database.sqlite.SQLiteDatabase;
import com.homedev.MyHome.model.Street;

import static com.homedev.MyHome.model.Street.Streets.*;

public class StreetDbHelper {

    public static void createStreetsTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                STREET_NAME + " TEXT NOT NULL)";
        db.execSQL(query);
    }


    public static final void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
    }

    public static final void insert(SQLiteDatabase db, Street street) {
        Long id = db.insert(TABLE_NAME, null, street.toContentValues());
        street.setId(id);
    }

}
