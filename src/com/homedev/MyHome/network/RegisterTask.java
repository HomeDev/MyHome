package com.homedev.MyHome.network;

import android.os.AsyncTask;
import android.util.Log;
import com.homedev.MyHome.ApiWorker;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegisterTask extends AsyncTask<URL, String, String> {

    private static final String LOG_TAG = "RegisterTask";
    private String apid;

    public RegisterTask(String apid) {
        Log.i(LOG_TAG, "constructor");
        this.apid = apid;
    }

    protected String doInBackground(URL... urls) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(ApiWorker.auth());

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

            Log.d(LOG_TAG, "Application deviceId: " + apid);

            nameValuePairs.add(new BasicNameValuePair("token", apid));
            httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPut);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                total.append(line);
            }

            Log.d(LOG_TAG, total.toString());

            JSONArray entries = new JSONArray(total.toString());
            int i;
            for (i = 0; i < entries.length(); i++)
            {
                JSONObject resultJson = entries.getJSONObject(i);
                if (resultJson.getString("status").equals("success")) {
                    Log.d(LOG_TAG, resultJson.toString());
                    break;
                }
            }

            return total.toString();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // This is called each time you call publishProgress()
    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate(progress);
    }

    // This is called when doInBackground() is finished
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
