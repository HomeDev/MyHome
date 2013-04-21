package com.homedev.MyHome;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.homedev.MyHome.db.RegistredAddressesDBHelper;
import com.homedev.MyHome.model.TextAddress;
import com.urbanairship.push.PushManager;

import com.homedev.MyHome.network.*;

import java.util.List;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";

    private ImageButton imageButton;
    private Button houseButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate");
        SQLiteOpenHelper helper = new RegistredAddressesDBHelper(
                this,
                getResources().getString(R.string.db_name),
                getResources().getInteger(R.integer.db_version));
        helper.getWritableDatabase();
        setContentView(R.layout.main);

        String apid = PushManager.shared().getAPID();

        if (apid != null) {
            new RegisterTask(apid).execute();
        }

        Log.i(LOG_TAG, "My Application onCreate - App APID: " + apid);

        imageButton = (ImageButton) findViewById(R.id.add);
        imageButton.setOnClickListener(new AddClickListener());

        houseButton = (Button) findViewById(R.id.house1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<TextAddress> textAddresses = RegistredAddressesDBHelper.getInstance().findAllAddresses();
        if (textAddresses.size()!=0){
            imageButton.setVisibility(View.GONE);
            houseButton.setText(textAddresses.get(textAddresses.size()-1).getAddress().replaceAll(";"," "));
            houseButton.setVisibility(View.VISIBLE);
        } else {
            imageButton.setVisibility(View.VISIBLE);
            houseButton.setVisibility(View.GONE);
        }
    }

    private final class AddClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, HomeListActivity.class);
            startActivityForResult(intent, 111);
        }
    }
}
