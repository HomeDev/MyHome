package com.homedev.MyHome.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.homedev.MyHome.R;

import static com.homedev.MyHome.db.DataBaseFieldsHierarchy.*;

public class RegistredAddressesDBHelper extends SQLiteOpenHelper{
    private Resources resources;

    public RegistredAddressesDBHelper(Context context, String app_name, int dbVersion) {
        super(context,app_name, null, dbVersion);
        resources=context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createStreetsTable(db);
        createAddressTable(db);
        createMessageTypeTable(db);
        createMessageTable(db);
    }

    private void createMessageTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Messages.TABLE_NAME + " (" +
                Messages.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Messages.INITIAL_ID + " INTEGER," +
                Messages.MESSAGE_TYPE + " INTEGER NOT NULL, " +
                Messages.MESSAGE_TEXT + " TEXT NOT NULL, " +
                Messages.ADDRESS_ID + " INTEGER NOT NULL, " +
                Messages.ACTIVITY_START_TIME + " DATETIME, " +
                Messages.ACTIVITY_END_TIME + " DATETIME, " +
                Messages.ACTIVITY_PROPOSAL_END + " DATETIME," +
                "FOREIGN KEY ("+Messages.MESSAGE_TYPE+") REFERENCES "+ MessageTypes.TABLE_NAME+"("+ MessageTypes.ID+"))";
        db.execSQL(query);
    }

    private void createMessageTypeTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + MessageTypes.TABLE_NAME + " (" +
                MessageTypes.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MessageTypes.MESSAGE_TYPE_NAME + " TEXT NOT NULL)";
        db.execSQL(query);
        addNewMessageType(db, resources.getString(R.string.hot));
        addNewMessageType(db, resources.getString(R.string.electricity));
        addNewMessageType(db, resources.getString(R.string.hotWater));
        addNewMessageType(db, resources.getString(R.string.coldWater));
        addNewMessageType(db, resources.getString(R.string.gas));
        addNewMessageType(db, resources.getString(R.string.announce));
    }

    private void addNewMessageType(SQLiteDatabase db, String messageTypeName) {
        String quary =  "INSERT INTO "+MessageTypes.TABLE_NAME +
                " ("+MessageTypes.MESSAGE_TYPE_NAME+") VALUES ('"+messageTypeName+"')";
        db.execSQL(quary);
    }

    private void createStreetsTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Streets.TABLE_NAME + " (" +
                Streets.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Streets.STREET_NAME + " TEXT NOT NULL)";
        db.execSQL(query);
    }

    private void createAddressTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Adresses.TABLE_NAME + " (" +
                Adresses.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Adresses.STREET_ID + " INTEGER NOT NULL," +
                Adresses.HOUSE_NUMBER + " TEXT NOT NULL, " +
                Adresses.HOUSE_SUFFIX + " TEXT, "+
                Adresses.HOUSE_INDEX +" TEXT," +
                "FOREIGN KEY ("+Adresses.STREET_ID+") REFERENCES "+ Streets.TABLE_NAME+"("+ Streets.ID+"))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE "+Messages.TABLE_NAME);
          db.execSQL("DROP TABLE "+Adresses.TABLE_NAME);
          db.execSQL("DROP TABLE "+Streets.TABLE_NAME);
          db.execSQL("DROP TABLE "+MessageTypes.TABLE_NAME);
          onCreate(db);
    }
}
