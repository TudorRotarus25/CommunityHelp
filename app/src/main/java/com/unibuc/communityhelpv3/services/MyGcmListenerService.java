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
import com.unibuc.communityhelpv3.activities.NotificationsActivity;
import com.unibuc.communityhelpv3.managers.MyPreferenceManager;
import com.unibuc.communityhelpv3.utils.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(String from, Bundle bundle) {
        Log.d(TAG, "From: " + from);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.d(TAG, currentDateTimeString);
        String first_time = bundle.getString("seen");
        String notification_id = bundle.getString("id");
        String data = bundle.getString("message");
        String title =  bundle.getString("title");
        String notification_type = bundle.getString("notification_type");
        String user_id = null;
        String task_id = null;
        if(notification_type != null && notification_type.equals("new_participant"))
        {
            user_id = bundle.getString("user_id");
            task_id = bundle.getString("task_id");
            Log.d(TAG, "user_id: " + user_id);
            Log.d(TAG, "task_id: " + task_id);


            Intent resultIntent = new Intent("notification_received_from_server");
            Log.d(TAG, "Broadcasting notification: " + data);
            resultIntent.putExtra("message", data);
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("notification_id", notification_id);

            resultIntent.putExtra("first_time", first_time);
            resultIntent.putExtra("type", notification_type);
            resultIntent.putExtra("date_received", currentDateTimeString);

            resultIntent.putExtra("user_id", user_id);
            resultIntent.putExtra("task_id", task_id);

            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(resultIntent);
        }

        //Log.d(TAG,"titlu "+ title);
        //Log.d(TAG, "message "+ data);
        Log.d(TAG, "notification_type: " + notification_type);


        sendNotification(title, data, notification_type);
    }


    private void sendNotification(String title, String data, String notification_type) {

        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();
        preferenceManager.set_notification_unique_id();
        int notification_unique_id = preferenceManager.get_notification_unique_id();


        Intent resultIntent = new Intent(getApplicationContext(), NotificationsActivity.class);
        String flag;

        flag = "show";
        Log.d(TAG, flag);
        showNotificationMessage(getApplicationContext(), title, data, "",notification_unique_id, resultIntent, flag, notification_type);
    }



    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */

    private void showNotificationMessage(Context context, String title, String message, String timeStamp, int notification_unique_id, Intent intent
            , String flag, String notification_type) {

        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, notification_unique_id, intent
                , flag, notification_type);
    }

}


