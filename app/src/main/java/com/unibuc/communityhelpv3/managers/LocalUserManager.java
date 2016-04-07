package com.unibuc.communityhelpv3.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Luci on 07/04/2016.
 */
public class LocalUserManager {
    Context context;
    private static final String PREF_NAME = "SHARED_PREFS";
    private static LocalUserManager localUserManager;

    private LocalUserManager(Context context){
        this.context = context;
    }

    public static LocalUserManager getInstance(Context context){
        if (localUserManager == null){
            localUserManager = new LocalUserManager(context);
        }
        return localUserManager;
    }

    public void storeLocalUserId(String userId){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE).edit();
        editor.putString("userId", userId);
        editor.commit();
    }

    public String getLocalUserId(){
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        String userId = prefs.getString("userId", "");
        return userId;
    }
}
