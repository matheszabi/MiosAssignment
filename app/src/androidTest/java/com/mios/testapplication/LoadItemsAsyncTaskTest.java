package com.mios.testapplication;


import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mios.testapplication.data.Device;
import com.mios.testapplication.withasynctask.LoadItemsAsyncTask;
import com.mios.testapplication.withasynctask.LoadItemsAsyncTaskCallback;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;


@RunWith(AndroidJUnit4.class)
public class LoadItemsAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public LoadItemsAsyncTaskTest() {
    }

    @Test
    public void doInBackground() throws Exception {

        Activity activity = activityActivityTestRule.getActivity();
        final CountDownLatch signal = new CountDownLatch(1);

        LoadItemsAsyncTask task = new LoadItemsAsyncTask(activity, new LoadItemsAsyncTaskCallback() {
            @Override
            public void onSuccess(ArrayList<Device> listDevice) {
                assertTrue(listDevice.size() > 0);//64
                signal.countDown();
            }

            @Override
            public void onFail(int responseCode, String errorMessageLocalized) {
                System.out.println("responseCode = " + responseCode);
                System.out.println("errorMessageLocalized = " + errorMessageLocalized);
                fail(errorMessageLocalized);
                signal.countDown();
            }
        });
        task.execute();

        signal.await();
    }
}