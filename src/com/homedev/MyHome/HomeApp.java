package com.homedev.MyHome;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class HomeApp extends Application {
    private static final String LOG_TAG = "HomeApp";

    private ApiWorker api;
    private String deviceId;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "onCreate HomeApp");

        api = new ApiWorker();
        deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        context = getApplicationContext();

        AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);

        // Take off initializes the services
        UAirship.takeOff(this, options);

        PushManager.shared().setIntentReceiver(IntentReceiver.class);
        PushManager.enablePush();
    }

    public ApiWorker getApi(){
        return this.api;
    }

    public String getDeviceId(){
        return this.deviceId;
    }
}
