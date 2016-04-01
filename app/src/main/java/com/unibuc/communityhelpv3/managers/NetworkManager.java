package com.unibuc.communityhelpv3.managers;

import android.util.Log;

import com.unibuc.communityhelpv3.pojos.CategoriesGetBody;
import com.unibuc.communityhelpv3.pojos.LoginPostBody;
import com.unibuc.communityhelpv3.pojos.interfaces.CategoriesListener;
import com.unibuc.communityhelpv3.pojos.interfaces.LoginListener;
import com.unibuc.communityhelpv3.rest.RestAPI;
import com.unibuc.communityhelpv3.rest.RestClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Tudor on 31.03.2016.
 */
public class NetworkManager {

    private final String TAG = getClass().getName();

    private static NetworkManager instance = null;
    private RestAPI restAPI;

    private NetworkManager() {
    }

    public static NetworkManager getInstance() {
        if(instance == null) {
            instance = new NetworkManager();
            instance.restAPI = (new RestClient()).getApiService();
        }

        return instance;
    }

    public void login(String firstName, String lastName, String facebookToken, String profilePicUri, final LoginListener callback) {
        Call<LoginPostBody> call = restAPI.LOGIN_POST_BODY_CALL(firstName, lastName, facebookToken, profilePicUri);
        call.enqueue(new Callback<LoginPostBody>() {
            @Override
            public void onResponse(Response<LoginPostBody> response, Retrofit retrofit) {
                if(response != null && response.body() != null && response.code() == 200) {
                    Log.i(TAG, "Login successful");
                    callback.onLoginSuccess(response.body());
                } else {
                    Log.e(TAG, "Login failed");
                    callback.onLoginFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Login failed: " + t.getMessage());
                callback.onLoginFailed();
            }
        });
    }

    public void getCategories(final CategoriesListener callback) {
        Call<CategoriesGetBody> call = restAPI.CATEORIES_GET_BODY_CALL();
        call.enqueue(new Callback<CategoriesGetBody>() {
            @Override
            public void onResponse(Response<CategoriesGetBody> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.code() == 200) {
                    callback.onCategoryGetSuccess(response.body());
                } else {
                    Log.e(TAG, "Get category failed");
                    callback.onCategoryGetFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Get category failed - " + t.getMessage());
                callback.onCategoryGetFailed();
            }
        });
    }
}
