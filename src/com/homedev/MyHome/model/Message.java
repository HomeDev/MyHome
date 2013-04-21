package com.homedev.MyHome.model;

import android.content.ContentValues;
import com.homedev.MyHome.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.homedev.MyHome.model.Message.Messages.*;

public class Message extends DomainEntry<Long> {

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

    private Long initialId;
    private Address address;
    private MessageType messageType;
    private String messageText;
    private Date activityStartTime;
    private Date activityEndTime;
    private Date activityProposalEndTime;


    @Override
    public ContentValues toContentValues() {
        ContentValues result = new ContentValues();
        if (getId()!=null){
           result.put(ID, getId());
        }
        result.put(INITIAL_ID, getInitialId());
        result.put(ADDRESS_ID, getAddress().getId());
        result.put(MESSAGE_TYPE, getMessageType().getId());
        result.put(MESSAGE_TEXT, getMessageText());
        if (getActivityStartTime()!=null){
             result.put(ACTIVITY_START_TIME, DateUtil.toSqlString(getActivityStartTime()));
        }
        if (getActivityEndTime()!=null){
            result.put(ACTIVITY_END_TIME, DateUtil.toSqlString(getActivityEndTime()));
        }
        if (getActivityProposalEndTime()!=null){
            result.put(ACTIVITY_PROPOSAL_END, DateUtil.toSqlString(getActivityProposalEndTime()));
        }
        return result;
    }

    public Long getInitialId() {
        return initialId;
    }

    public void setInitialId(Long initialId) {
        this.initialId = initialId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Date getActivityProposalEndTime() {
        return activityProposalEndTime;
    }

    public void setActivityProposalEndTime(Date activityProposalEndTime) {
        this.activityProposalEndTime = activityProposalEndTime;
    }
}
