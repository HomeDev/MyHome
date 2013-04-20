package com.homedev.MyHome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.urbanairship.push.PushManager;

public class MainActivity extends Activity {
    private static final String LOG_TAG = "MainActivity";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate");

        setContentView(R.layout.main);

        String apid = PushManager.shared().getAPID();
        Log.i(LOG_TAG, "My Application onCreate - App APID: " + apid);
    }
}
