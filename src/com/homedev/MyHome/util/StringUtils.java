package com.homedev.MyHome.util;

import java.text.SimpleDateFormat;

public class StringUtils {
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isNullOrEmpty(String value){
        return value==null || "".equals(value.trim());
    }
}
