package com.mios.testapplication.devicedetail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mios.testapplication.R;
import com.mios.testapplication.data.Device;

public class DeviceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);


        ImageView ivDevice = findViewById(R.id.ivDevice);
        TextView tvPKDevice = findViewById(R.id.tvPKDevice);
        TextView tvSN = findViewById(R.id.tvSN);
        TextView tvMAC = findViewById(R.id.tvMAC);
        TextView tvFirmware = findViewById(R.id.tvFirmware);
        TextView tvModel = findViewById(R.id.tvModel);

        Bundle b = getIntent().getBundleExtra("bundle");
        Device device  = b.getParcelable("Device");

        String platform = device.getPlatform();

        String model = "Vera Edge";
        int drawableId = R.drawable.vera_edge_big;
        if (platform != null) {
            switch (platform) {
                case "Sercomm G450":
                    drawableId = R.drawable.vera_plus_big;
                    model = "Vera Plus";
                    break;
                case "Sercomm G550":
                    drawableId = R.drawable.vera_secure_big;
                    model = "Vera Secure";
                    break;
                case "MiCasaVerde VeraLite":
                    drawableId = R.drawable.vera_edge_big;
                    model = "Vera Edge";
                    break;
                case "Sercomm NA900":
                    drawableId = R.drawable.vera_edge_big;
                    model = "Vera Edge";
                    break;
                case "Sercomm NA301":
                    drawableId = R.drawable.vera_edge_big;
                    model = "Vera Edge";
                    break;
                case "Sercomm NA930":
                    drawableId = R.drawable.vera_edge_big;
                    model = "Vera Edge";
                    break;
                default:
                    drawableId = R.drawable.vera_edge_big;
                    model = "Vera Edge";
            }
        }
        Drawable img = getDrawable(drawableId);
        ivDevice.setImageDrawable(img);

        tvPKDevice.setText(device.getPK_Device());
        tvSN.setText(device.getPK_Device());
        tvMAC.setText(device.getMacAddress());
        tvFirmware.setText(device.getFirmware());
        tvModel.setText(model);
    }
}
