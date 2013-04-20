package com.homedev.MyHome;

public class ApiWorker {
    private static String apiUrl = "http://mysterious-mountain-5529.herokuapp.com/api/v1";

    public static String auth(){
        return apiUrl + "/auth";
    }

    public static String ping(){
        return apiUrl + "/system/ping";
    }

    public static String subscribe() {
        return apiUrl + "/subscribe";
    }
}
