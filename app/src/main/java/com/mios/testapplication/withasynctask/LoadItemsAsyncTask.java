package com.mios.testapplication.withasynctask;

import android.app.Activity;
import android.os.AsyncTask;

import com.mios.testapplication.R;
import com.mios.testapplication.URL_Settings;
import com.mios.testapplication.data.Device;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.*;

public class LoadItemsAsyncTask extends AsyncTask<Void, Void, String> implements URL_Settings {

    private Activity activity;
    private LoadItemsAsyncTaskCallback callback;

    public LoadItemsAsyncTask(Activity activity, LoadItemsAsyncTaskCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    protected int responseCode;
    protected String errorMessageLocalized;

    private boolean executedWithSuccess = false;
    private ArrayList<Device> listDevice;

    @Override
    protected String doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        try {

            URL url = new URL(DEVICE_LIST_URL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.connect();

            responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                // can be used a library, which will bind, and parse it.
                String jsonString = sb.toString();

                parseJSON(jsonString);

            }
            else if(responseCode == HttpURLConnection.HTTP_NOT_FOUND || responseCode >= 400){
                // load the json from cache, if the URL not valid anymore...
                parseJSON(loadJsonRaw());
            }
        }catch (java.net.UnknownHostException uhEx){
            errorMessageLocalized = "Failed to connect to server";
            uhEx.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            errorMessageLocalized = ex.getLocalizedMessage();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private String loadJsonRaw() throws IOException {
        InputStream is = activity.getResources().openRawResource(R.raw.items);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

    private void parseJSON(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.has("Devices")){
            JSONArray jsonarray = (JSONArray) jsonObject.get("Devices");
            listDevice = new ArrayList<>(jsonarray.length());
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonDevice = jsonarray.getJSONObject(i);
                Device device = new Device();
                if(jsonDevice.has("PK_Device")){
                    device.setPK_Device(jsonDevice.getString("PK_Device"));
                }
                if(jsonDevice.has("MacAddress")){
                    device.setMacAddress(jsonDevice.getString("MacAddress"));
                }
                if(jsonDevice.has("PK_DeviceType")){
                    device.setPK_DeviceType(jsonDevice.getString("PK_DeviceType"));
                }
                if(jsonDevice.has("PK_DeviceSubType")){
                    device.setPK_DeviceSubType(jsonDevice.getString("PK_DeviceSubType"));
                }
                if(jsonDevice.has("Server_Device")){
                    device.setServer_Device(jsonDevice.getString("Server_Device"));
                }
                if(jsonDevice.has("Server_Event")){
                    device.setServer_Event(jsonDevice.getString("Server_Event"));
                }
                if(jsonDevice.has("PK_Account")){
                    device.setPK_Account(jsonDevice.getString("PK_Account"));
                }
                if(jsonDevice.has("Firmware")){
                    device.setFirmware(jsonDevice.getString("Firmware"));
                }
                if(jsonDevice.has("Server_Account")){
                    device.setServer_Account(jsonDevice.getString("Server_Account"));
                }
                if(jsonDevice.has("InternalIP")){
                    device.setInternalIP(jsonDevice.getString("InternalIP"));
                }
                if(jsonDevice.has("LastAliveReported")){
                    device.setLastAliveReported(jsonDevice.getString("LastAliveReported"));
                }
                if(jsonDevice.has("Platform")){
                    device.setPlatform(jsonDevice.getString("Platform"));
                }
                listDevice.add(device);

            }
            sort(listDevice);

            executedWithSuccess = true;
        }
        else{
            // some error probably, parse the error, but there is no specification now
            errorMessageLocalized = "There is no Devices key in the response from server";
        }
    }


    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // for UI:
        if(callback != null){
            if(executedWithSuccess) {
                callback.onSuccess(listDevice);
            }
            else{
                callback.onFail(responseCode, errorMessageLocalized);
            }
        }
    }
}
