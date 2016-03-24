package com.unibuc.communityhelpv3;

import android.app.Application;

import com.unibuc.communityhelpv3.rest.RestClient;

/**
 * Created by Tudor on 17.03.2016.
 */
public class MyApplication extends Application {

    public static final String SHARED_PREFERENCES_TAG = "SHARED_PREFERENCES_TAG";

    private RestClient restClient;

    public MyApplication() {
        restClient = new RestClient();
    }

    public RestClient getRestClient() {
        return restClient;
    }
}
