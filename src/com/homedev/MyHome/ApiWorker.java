package com.homedev.MyHome;

public class ApiWorker {
    private String apiUrl = "http://mysterious-mountain-5529.herokuapp.com/api/v1";

    public String auth(){
        return apiUrl + "/auth";
    }

    public String ping(){
        return apiUrl + "/system/ping";
    }

    public String subscribe() {
        return apiUrl + "/subscribe";
    }
}
