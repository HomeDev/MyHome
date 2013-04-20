package com.homedev.MyHome.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String value){
        return value==null || "".equals(value.trim());
    }
}
