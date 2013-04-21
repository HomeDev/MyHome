package com.homedev.MyHome.db;

import android.database.sqlite.SQLiteDatabase;
import com.homedev.MyHome.model.Message;
import com.homedev.MyHome.model.MessageType;

import static com.homedev.MyHome.model.MessageType.MessageTypes.*;

public class MessageTypeDbHelper {

    public final static void createMessageTypeTable(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MESSAGE_TYPE_NAME + " TEXT NOT NULL)";
        db.execSQL(query);
    }

    public final static void insert(SQLiteDatabase db, MessageType messageType) {
        Long id = db.insert(TABLE_NAME, null, messageType.toContentValues());
        messageType.setId(id);
    }

    public final static MessageType insert(SQLiteDatabase db, String name) {
        MessageType messageType = new MessageType(name);
        insert(db, messageType);
        return messageType;
    }

    public static final void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE "+ TABLE_NAME);
    }
}
