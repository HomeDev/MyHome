package com.homedev.MyHome.db;

import android.database.sqlite.SQLiteDatabase;
import com.homedev.MyHome.model.Address;
import com.homedev.MyHome.model.Message;
import com.homedev.MyHome.model.MessageType;

import static com.homedev.MyHome.model.Message.Messages;
import static com.homedev.MyHome.model.Message.Messages.*;
import static com.homedev.MyHome.model.Message.Messages.*;
import static com.homedev.MyHome.model.MessageType.*;
import static com.homedev.MyHome.model.MessageType.MessageTypes.ID;


public class MessageDbHelper {

    public final static void createMessageTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                Messages.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                INITIAL_ID + " INTEGER," +
                MESSAGE_TYPE + " INTEGER NOT NULL, " +
                MESSAGE_TEXT + " TEXT NOT NULL, " +
                ADDRESS_ID + " INTEGER NOT NULL, " +
                ACTIVITY_START_TIME + " DATETIME, " +
                ACTIVITY_END_TIME + " DATETIME, " +
                ACTIVITY_PROPOSAL_END + " DATETIME," +
                "FOREIGN KEY ("+ MESSAGE_TYPE+") REFERENCES "+ MessageTypes.TABLE_NAME+"("+ MessageTypes.ID+"))";
        db.execSQL(query);
    }

    public static final void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE "+ TABLE_NAME);
    }
}
