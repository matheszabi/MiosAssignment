package com.mios.testapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 JSON elements
  */
public class Device implements Comparable<Device>, Parcelable {

    @SerializedName("PK_Device")
    @Expose
    private String PK_Device;//12315977

    @SerializedName("MacAddress")
    @Expose
    private String MacAddress;//78:45:61:A0:E1:BA"


    @SerializedName("PK_DeviceType")
    @Expose
    private String PK_DeviceType;//1


    @SerializedName("PK_DeviceSubType")
    @Expose
    private String PK_DeviceSubType;//2


    @SerializedName("Server_Device")
    @Expose
    private String Server_Device;//vera-us-oem-device12.mios.com"


    @SerializedName("Server_Event")
    @Expose
    private String Server_Event;//vera-us-oem-event12.mios.com"


    @SerializedName("PK_Account")
    @Expose
    private String PK_Account;//812341


    @SerializedName("Firmware")
    @Expose
    private String Firmware;//1.7.1235"


    @SerializedName("Server_Account")
    @Expose
    private String Server_Account;//vera-us-oem-account12.mios.com"


    @SerializedName("InternalIP")
    @Expose
    private String InternalIP;//192.168.6.128"


    @SerializedName("LastAliveReported")
    @Expose
    private String LastAliveReported;//2018-02-20 07:14:14"


    @SerializedName("Platform")
    @Expose
    private String Platform;//"Sercomm NA301"

    public Device(){}


    public String getPK_Device() {
        return PK_Device;
    }

    public void setPK_Device(String PK_Device) {
        this.PK_Device = PK_Device;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getPK_DeviceType() {
        return PK_DeviceType;
    }

    public void setPK_DeviceType(String PK_DeviceType) {
        this.PK_DeviceType = PK_DeviceType;
    }

    public String getPK_DeviceSubType() {
        return PK_DeviceSubType;
    }

    public void setPK_DeviceSubType(String PK_DeviceSubType) {
        this.PK_DeviceSubType = PK_DeviceSubType;
    }

    public String getServer_Device() {
        return Server_Device;
    }

    public void setServer_Device(String server_Device) {
        Server_Device = server_Device;
    }

    public String getServer_Event() {
        return Server_Event;
    }

    public void setServer_Event(String server_Event) {
        Server_Event = server_Event;
    }

    public String getPK_Account() {
        return PK_Account;
    }

    public void setPK_Account(String PK_Account) {
        this.PK_Account = PK_Account;
    }

    public String getFirmware() {
        return Firmware;
    }

    public void setFirmware(String firmware) {
        Firmware = firmware;
    }

    public String getServer_Account() {
        return Server_Account;
    }

    public void setServer_Account(String server_Account) {
        Server_Account = server_Account;
    }

    public String getInternalIP() {
        return InternalIP;
    }

    public void setInternalIP(String internalIP) {
        InternalIP = internalIP;
    }

    public String getLastAliveReported() {
        return LastAliveReported;
    }

    public void setLastAliveReported(String lastAliveReported) {
        LastAliveReported = lastAliveReported;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    @Override
    public String toString() {
        return "Device{" +
                "PK_Device='" + PK_Device + '\'' +
                ", MacAddress='" + MacAddress + '\'' +
                ", PK_DeviceType='" + PK_DeviceType + '\'' +
                ", PK_DeviceSubType='" + PK_DeviceSubType + '\'' +
                ", Server_Device='" + Server_Device + '\'' +
                ", Server_Event='" + Server_Event + '\'' +
                ", PK_Account='" + PK_Account + '\'' +
                ", Firmware='" + Firmware + '\'' +
                ", Server_Account='" + Server_Account + '\'' +
                ", InternalIP='" + InternalIP + '\'' +
                ", LastAliveReported='" + LastAliveReported + '\'' +
                ", Platform='" + Platform + '\'' +
                '}';
    }


    // Parcelable stuff  - start
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PK_Device);
        dest.writeString(MacAddress);
        dest.writeString(PK_DeviceType);
        dest.writeString(PK_DeviceSubType);
        dest.writeString(Server_Device);
        dest.writeString(Server_Event);
        dest.writeString(PK_Account);
        dest.writeString(Firmware);
        dest.writeString(Server_Account);
        dest.writeString(InternalIP);
        dest.writeString(LastAliveReported);
        dest.writeString(Platform);
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    public Device(Parcel parcel) {
        PK_Device = parcel.readString();
        MacAddress = parcel.readString();
        PK_DeviceType = parcel.readString();
        PK_DeviceSubType = parcel.readString();
        Server_Device = parcel.readString();
        Server_Event = parcel.readString();
        PK_Account = parcel.readString();
        Firmware = parcel.readString();
        Server_Account = parcel.readString();
        InternalIP = parcel.readString();
        LastAliveReported = parcel.readString();
        Platform = parcel.readString();
    }
    // Parcelable stuff - end


    // Comparable implementation
    // it is stored as String and is sorted as String, convert to Int, if Int is needed
    @Override
    public int compareTo(Device other){
        if(other == null){
            return 1;
        }
        return PK_Device.compareTo(other.PK_Device);
    }

}
