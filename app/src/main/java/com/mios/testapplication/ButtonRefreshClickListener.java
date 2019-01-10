package com.mios.testapplication;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mios.testapplication.data.Device;
import com.mios.testapplication.error.ErrorDialog;
import com.mios.testapplication.retrofit.ApiServiceRetrofit;
import com.mios.testapplication.retrofit.DeviceList;
import com.mios.testapplication.withasynctask.LoadItemsAsyncTask;
import com.mios.testapplication.withasynctask.LoadItemsAsyncTaskCallbackImpl;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ButtonRefreshClickListener implements View.OnClickListener, URL_Settings {

    // choose implementation of calling: add it to a config or a UI settings
    String webserviceCallMode = "AsyncTask";

    private Button bt;
    private ListView lvItems;
    private String origText;

    public ButtonRefreshClickListener(Button bt, ListView lvItems) {
        this.bt = bt;
        this.lvItems = lvItems;
    }

    @Override
    public void onClick(View view) {
        origText = bt.getText().toString();
        bt.setText("Please wait, loading...");


        switch (webserviceCallMode) {
            case "AsyncTask":
                callWithAsyncTask((Activity) view.getContext(), bt, origText, lvItems);
                break;
            case "Volley":
                callWithVolley(view.getContext());
                break;
            case "Retrofit":
                callWithRetrofit(view.getContext());
                break;
        }

    }


    private void callWithVolley(Context ctx) {
        RequestQueue queue = Volley.newRequestQueue(ctx); // ctx is the context

        StringRequest req = new StringRequest(Request.Method.GET, DEVICE_LIST_URL,
                response -> {
                    bt.setVisibility(View.GONE);
                    bt.setText(origText);
                    lvItems.setVisibility(View.VISIBLE);
                    //  handle the response: with GSON
                    Gson gson = new Gson();

                    DeviceList deviceList = gson.fromJson(response, DeviceList.class);
                    ArrayList<Device> devices = deviceList.getDeviceList();
                    Collections.sort(devices);
                    lvItems.setAdapter(new DeviceListAdapter(bt.getContext(), devices));
                },
                error -> {
                    bt.setVisibility(View.VISIBLE);
                    bt.setText(origText);
                    lvItems.setVisibility(View.GONE);
                    // handle error:
                    ErrorDialog.showDialog(ctx, error.toString());
                }
        );
        queue.add(req);
    }


    private void callWithRetrofit(Context ctx) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DEVICE_LIST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServiceRetrofit apiServiceRetrofit = retrofit.create(ApiServiceRetrofit.class);
        Call<DeviceList> call = apiServiceRetrofit.getMyJSON();


        call.enqueue(new Callback<DeviceList>() {


            @Override
            public void onResponse(Call<DeviceList> call, retrofit2.Response<DeviceList> response) {
                bt.setVisibility(View.GONE);
                bt.setText(origText);
                lvItems.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    DeviceList deviceList = response.body();
                    ArrayList<Device> devices = deviceList.getDeviceList();
                    Collections.sort(devices);
                    lvItems.setAdapter(new DeviceListAdapter(bt.getContext(), devices));
                }
            }

            @Override
            public void onFailure(Call<DeviceList> call, Throwable t) {
                bt.setVisibility(View.VISIBLE);
                bt.setText(origText);
                lvItems.setVisibility(View.GONE);

                ErrorDialog.showDialog(ctx, t.getLocalizedMessage());
            }
        });
    }


    private void callWithAsyncTask(Activity activity, Button bt, String origText, ListView lvItems) {
        LoadItemsAsyncTask LoadItemsAsyncTask = new LoadItemsAsyncTask(activity, new LoadItemsAsyncTaskCallbackImpl(activity, bt, origText, lvItems));
        LoadItemsAsyncTask.execute();
    }
}
