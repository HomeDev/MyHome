package com.homedev.MyHome.shared;

import android.util.Log;
import com.homedev.MyHome.HomeApp;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
    private static final String LOG_TAG = "AppProperties";
    private static Properties ourInstance;

    public static Properties getProperties() {
        if (ourInstance == null) {
            ourInstance = new Properties();
            try {
                //load a properties file from class path, inside static method
                ourInstance.load(HomeApp.class.getClassLoader().getResourceAsStream("assets/application.properties"));
                Log.d(LOG_TAG, ourInstance.toString());
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ourInstance;
    }

    private ApplicationProperties() {
    }

    public static String getApiKey(){
        Log.d(LOG_TAG, getProperties().getProperty("API_KEY"));
        return getProperties().getProperty("API_KEY");
    }
}
