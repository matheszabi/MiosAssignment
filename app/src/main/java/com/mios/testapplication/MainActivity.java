package com.mios.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ListView;

import com.mios.testapplication.data.Device;
import com.mios.testapplication.devicedetail.DeviceDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lvItems)
    ListView lvItems;

    @BindView(R.id.btItemsEmpty)
    Button btItemsEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btItemsEmpty.setOnClickListener(new ButtonRefreshClickListener(btItemsEmpty, lvItems));

        btItemsEmpty.performClick();


        lvItems.setOnItemClickListener((parent, view, position, id) -> {

            Animation fadeout = new AlphaAnimation(1.f, 0.f);
            fadeout.setDuration(500);
            fadeout.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    DeviceListAdapter adapter = (DeviceListAdapter) lvItems.getAdapter();
                    Device device = adapter.getItem(position);

                    Intent intent = new Intent(MainActivity.this, DeviceDetailActivity.class);

                    Bundle b = new Bundle();
                    b.putParcelable("Device", device);
                    intent.putExtra("bundle", b);
                    startActivity(intent);
                }
            });
            view.startAnimation(fadeout);


        });


        // should be an animation with a background change color
        lvItems.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog diaBox = showDeleteDialog(position);
            diaBox.show();

            return true;
        });
    }

    private AlertDialog showDeleteDialog(final int position) {
        DeviceListAdapter adapter = (DeviceListAdapter) lvItems.getAdapter();
        Device device = adapter.getItem(position);

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setPositiveButton("Delete", (dialog, whichButton) -> {
                    adapter.remove(device);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                })
                .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                .create();

        return myQuittingDialogBox;

    }
}
