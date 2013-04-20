package com.homedev.MyHome.db;

public abstract class DataBaseFieldsHierarchy {
    private DataBaseFieldsHierarchy() {
        throw new UnsupportedOperationException();
    }

    public static final class Adresses {
        private Adresses() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="address";
        public final static String ID = "address_id";
        public final static String STREET_ID = "street_id";
        public final static String HOUSE_NUMBER = "house_number";
        public final static String HOUSE_INDEX = "house_index";
        public final static String HOUSE_SUFFIX = "house_suffix";
    }

    public static final class Streets {
        private Streets() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="street";
        public final static String ID = "street_id";
        public final static String STREET_NAME = "street_name";
    }

    public static final class Messages{
        private Messages() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="message";
        public final static String ID = "message_id";
        public final static String INITIAL_ID = "initial_message_id";
        public final static String ADDRESS_ID="address_id";
        public final static String MESSAGE_TYPE="message_type";
        public final static String MESSAGE_TEXT="message_text";
        public final static String ACTIVITY_START_TIME = "activity_start_time";
        public final static String ACTIVITY_END_TIME = "activity_end_time";
        public final static String ACTIVITY_PROPOSAL_END = "activity_proposal_end";
    }

    public static final class MessageTypes{
        private MessageTypes() {
            throw new UnsupportedOperationException();
        }
        public final static String TABLE_NAME="message_type";
        public final static String ID = "message_type_id";
        public final static String MESSAGE_TYPE_NAME = "message_type_name";
    }
}
