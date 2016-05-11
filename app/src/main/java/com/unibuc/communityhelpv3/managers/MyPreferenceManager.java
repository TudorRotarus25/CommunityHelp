package com.unibuc.communityhelpv3.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Serban Theodor on 09-May-16.
 */
public class MyPreferenceManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Sharedpref file name
    private static final String PREF_NAME = "android_hotel";

    // All Shared Preferences Keys
    private static final String KEY_GCM_TOKEN = "token";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_NOTFICATION_UNIQUE_ID = "notification_unique_id";

    Context _context;

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public void add_gcm_token(String token){
        editor.putString(KEY_GCM_TOKEN, token);
        editor.commit();
    }

    public String get_gcm_token(){
        return pref.getString(KEY_GCM_TOKEN, null);
    }

    public void add_notification_unique_id(int notification_unique_id)
    {
        editor.putInt(KEY_NOTFICATION_UNIQUE_ID, notification_unique_id);
        editor.commit();
    }

    public void set_notification_unique_id()
    {
        int aux = pref.getInt(KEY_NOTFICATION_UNIQUE_ID, 0);
        aux++;
        editor.putInt(KEY_NOTFICATION_UNIQUE_ID, aux);
        editor.commit();
    }

    public int get_notification_unique_id()
    {
        return pref.getInt(KEY_NOTFICATION_UNIQUE_ID, 0);
    }

    public void addNotification(String notification) {

        // get old notifications
        String oldNotifications = getNotifications();

        if (oldNotifications != null) {
            oldNotifications += "|" + notification;
        } else {
            oldNotifications = notification;
        }

        editor.putString(KEY_NOTIFICATIONS, oldNotifications);
        editor.commit();
    }

    public String getNotifications() {
        return pref.getString(KEY_NOTIFICATIONS, null);
    }

    public void clear_messages()
    {
        editor.remove(KEY_NOTIFICATIONS);
        editor.commit();
    }
}