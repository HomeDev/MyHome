package com.homedev.MyHome.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.homedev.MyHome.R;
import com.homedev.MyHome.model.MessageType;
import static com.homedev.MyHome.model.MessageType.MessageTypes.*;

public class RegistredAddressesDBHelper extends SQLiteOpenHelper{
    private Resources resources;

    public RegistredAddressesDBHelper(Context context, String app_name, int dbVersion) {
        super(context,app_name, null, dbVersion);
        resources=context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StreetDbHelper.createStreetsTable(db);
        AddressDbHelper.createAddressTable(db);
        MessageTypeDbHelper.createMessageTypeTable(db);
        addMessageTypes(db);
        MessageDbHelper.createMessageTable(db);

    }

    private void addMessageTypes(SQLiteDatabase db) {
        MessageTypeDbHelper.insert(db, resources.getString(R.string.hot));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.electricity));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.hotWater));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.coldWater));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.gas));
        MessageTypeDbHelper.insert(db, resources.getString(R.string.announce));
    }



     @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          MessageDbHelper.dropTable(db);
          MessageTypeDbHelper.dropTable(db);
          AddressDbHelper.dropTable(db);
          StreetDbHelper.dropTable(db);
          onCreate(db);
    }


}
