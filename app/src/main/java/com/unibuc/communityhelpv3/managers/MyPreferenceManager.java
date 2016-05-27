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
    private static final String PREF_NAME = "community_help";

    // All Shared Preferences Keys
    private static final String KEY_GCM_TOKEN = "token";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_NOTFICATION_UNIQUE_ID = "notification_unique_id";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_FACEBOOK_TOKEN = "facebook_token";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_PROFILE_PIC = "profile_pic";
    private static final String KEY_CURRENT_USER_ID= "current_user_id";
    private static final String KEY_FACEBOOK_LOGGED_IN = "facebook_logged_in";
    private static final String KEY_USER_RESOURCES = "user_resources";

    Context _context;

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public void set_user_resources(String user_resources)
    {
        editor.putString(KEY_USER_RESOURCES, user_resources);
        editor.commit();
    }

    public String get_user_resources()
    {
        return pref.getString(KEY_USER_RESOURCES, null);
    }

    public void set_facebook_logged_in(boolean facebook_logged_in)
    {
        editor.putBoolean(KEY_FACEBOOK_LOGGED_IN, facebook_logged_in);
        editor.commit();
    }

    public boolean get_facebook_logged_in()
    {
        return pref.getBoolean(KEY_FACEBOOK_LOGGED_IN, false);
    }

    public void add_current_user_id(String current_user_id)
    {
        editor.putString(KEY_CURRENT_USER_ID, current_user_id);
        editor.commit();
    }

    public String get_current_user_id()
    {
        return pref.getString(KEY_CURRENT_USER_ID, null);
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

    public void addLocationInserted() {
        editor.putBoolean(KEY_LOCATION, true);
        editor.commit();
    }

    public boolean isLocationInserted() {
        return pref.getBoolean(KEY_LOCATION, false);
    }

    public void writeProfile(String token, String firstName, String lastName, String profilePicUri) {
        editor.putString(KEY_FACEBOOK_TOKEN, token);
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.putString(KEY_LAST_NAME, lastName);
        editor.putString(KEY_PROFILE_PIC, profilePicUri);
        editor.commit();
    }

    public String getFacebookToken() {
        return pref.getString(KEY_FACEBOOK_TOKEN, null);
    }
}
