package com.mios.testapplication.withasynctask;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mios.testapplication.DeviceListAdapter;
import com.mios.testapplication.data.Device;
import com.mios.testapplication.error.ErrorDialog;

import java.util.ArrayList;

public class LoadItemsAsyncTaskCallbackImpl implements LoadItemsAsyncTaskCallback {

    private Context ctx;
    private Button bt;
    private String origText;
    private ListView lvItems;

    public LoadItemsAsyncTaskCallbackImpl(Context ctx, Button bt, String origText, ListView lvItems){
        this.ctx = ctx;
        this.bt = bt;
        this.origText = origText;
        this.lvItems = lvItems;
    }

    @Override
    public void onSuccess(ArrayList<Device> listDevice) {
        bt.setVisibility(View.GONE);
        bt.setText(origText);
        lvItems.setVisibility(View.VISIBLE);

        lvItems.setAdapter(new DeviceListAdapter(bt.getContext(), listDevice));
    }

    @Override
    public void onFail(int responseCode, String errorMessageLocalized) {
        bt.setVisibility(View.VISIBLE);
        bt.setText(origText);
        lvItems.setVisibility(View.GONE);

        ErrorDialog.showDialog(ctx, "Response code: "+responseCode+"\nMessage: "+errorMessageLocalized);
    }
}

