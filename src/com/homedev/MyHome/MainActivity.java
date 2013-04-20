package com.homedev.MyHome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import com.urbanairship.push.PushManager;

import com.homedev.MyHome.network.*;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    private ImageButton imageButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate");

        setContentView(R.layout.main);

        String apid = PushManager.shared().getAPID();

        if (apid != null) {
            new RegisterTask(apid).execute();
        }

        Log.i(LOG_TAG, "My Application onCreate - App APID: " + apid);

        imageButton = (ImageButton)findViewById(R.id.add);
        imageButton.setOnClickListener(new AddClickListener());

    }

    private final class AddClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, HomeListActivity.class);
            startActivity(intent);
        }
    }
}
