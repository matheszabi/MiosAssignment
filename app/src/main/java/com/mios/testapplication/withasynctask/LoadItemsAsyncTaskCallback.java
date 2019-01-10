package com.mios.testapplication.withasynctask;

import com.mios.testapplication.data.Device;

import java.util.ArrayList;

public interface LoadItemsAsyncTaskCallback {
    void onSuccess(ArrayList<Device> listDevice);

    void onFail(int responseCode, String errorMessageLocalized);
}
