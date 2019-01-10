package com.mios.testapplication.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mios.testapplication.data.Device;

import java.util.ArrayList;

public class DeviceList {
    @SerializedName("Device")
    @Expose
    private ArrayList<Device> deviceList = null;

    public ArrayList<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(ArrayList<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
