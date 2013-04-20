package com.homedev.MyHome;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class HomeApp extends Application {
    private static final String LOG_TAG = "HomeApp";

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "onCreate HomeApp");

        context = getApplicationContext();

        AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);

        // Take off initializes the services
        UAirship.takeOff(this, options);

        PushManager.shared().setIntentReceiver(IntentReceiver.class);
        PushManager.enablePush();
    }
}
