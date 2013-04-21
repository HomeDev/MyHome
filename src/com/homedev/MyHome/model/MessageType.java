package com.homedev.MyHome.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.homedev.MyHome.R;
import com.homedev.MyHome.model.DomainEntry;

import static com.homedev.MyHome.model.MessageType.MessageTypes.*;

public class MessageType extends DomainEntry<Long> {

    public static final class MessageTypes{
        private MessageTypes() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="message_type";
        public final static String ID = "message_type_id";
        public final static String MESSAGE_TYPE_NAME = "message_type_name";
    }

    private String messageTypeName;

    public MessageType(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }

    public void setMessageTypeName(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues result = new ContentValues();
        if (getId()!=null){
            result.put(ID, getId());
        }
        result.put(MESSAGE_TYPE_NAME, getMessageTypeName());
        return result;
    }

}
