package com.unibuc.communityhelpv3.services;

/**
 * Created by Serban Theodor on 09-May-16.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;
import com.unibuc.communityhelpv3.MyApplication;
import com.unibuc.communityhelpv3.activities.MainActivity;
import com.unibuc.communityhelpv3.managers.MyPreferenceManager;
import com.unibuc.communityhelpv3.utils.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(String from, Bundle bundle) {
        Log.d(TAG, "From: " + from);

        String data = bundle.getString("message");
        String title =  bundle.getString("title");

        //Log.d(TAG,"titlu "+ title);
        //Log.d(TAG, "message "+ data);

        sendNotification(title, data);
    }


    private void sendNotification(String title, String data) {

        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();
        preferenceManager.set_notification_unique_id();
        int notification_unique_id = preferenceManager.get_notification_unique_id();


        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        String flag;

        flag = "show";
        Log.d(TAG, flag);
        showNotificationMessage(getApplicationContext(), title, data, "",notification_unique_id, resultIntent, flag);
    }



    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */

    private void showNotificationMessage(Context context, String title, String message, String timeStamp, int notification_unique_id, Intent intent, String flag) {

        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, notification_unique_id, intent, flag);
    }

}


