package com.mios.testapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mios.testapplication.data.Device;

import java.util.ArrayList;

public class DeviceListAdapter extends ArrayAdapter<Device> {

    private ArrayList<Device> listDevice;

    public DeviceListAdapter(Context context, ArrayList<Device> listDevice) {
        super(context, R.layout.device_list_item, listDevice);
        this.listDevice = listDevice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.device_list_item, null);
        }
        Device device = getItem(position);
        String platform = device.getPlatform();

        int drawableId = R.drawable.vera_edge_big;
        if (platform != null) {
            switch (platform) {
                case "Sercomm G450":
                    drawableId = R.drawable.vera_plus_big;
                    break;
                case "Sercomm G550":
                    drawableId = R.drawable.vera_secure_big;
                    break;
                case "MiCasaVerde VeraLite":
                    drawableId = R.drawable.vera_edge_big;
                    break;
                case "Sercomm NA900":
                    drawableId = R.drawable.vera_edge_big;
                    break;
                case "Sercomm NA301":
                    drawableId = R.drawable.vera_edge_big;
                    break;
                case "Sercomm NA930":
                    drawableId = R.drawable.vera_edge_big;
                    break;
                default:
                    drawableId = R.drawable.vera_edge_big;
            }
        }

        ImageView ivDevice = v.findViewById(R.id.ivDevice);
        Drawable img = getContext().getDrawable(drawableId);
        ivDevice.setImageDrawable(img);

        TextView tvPKDevice = v.findViewById(R.id.tvPKDevice);
        tvPKDevice.setText(device.getPK_Device());


        //The list must be sorted by PK_Device(this is the same with SN from the mocks)
        TextView tvSN = v.findViewById(R.id.tvSN);
        tvSN.setText(device.getPK_Device());

        return v;
    }
}

