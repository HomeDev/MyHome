package com.homedev.MyHome.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static String toSqlString(Date date){
        return dateFormat.format(date);
    }
}
