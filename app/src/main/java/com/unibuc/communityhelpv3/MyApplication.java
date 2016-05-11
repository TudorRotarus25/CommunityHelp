package com.unibuc.communityhelpv3;

import android.app.Application;

import com.unibuc.communityhelpv3.managers.MyPreferenceManager;
import com.unibuc.communityhelpv3.rest.RestClient;

/**
 * Created by Tudor on 17.03.2016.
 */
public class MyApplication extends Application {

    public static final String SHARED_PREFERENCES_TAG = "SHARED_PREFERENCES_TAG";

    private RestClient restClient;

    private static MyApplication mInstance;

    private MyPreferenceManager pref;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public MyApplication() {
        restClient = new RestClient();
    }

    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }
        return pref;
    }

    public RestClient getRestClient() {
        return restClient;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
