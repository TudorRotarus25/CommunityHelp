package com.unibuc.communityhelpv3.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.unibuc.communityhelpv3.MyApplication;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.LoginPostBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = "LoginActivity";

    MyApplication app;

    CallbackManager callbackManager;
    LoginButton facebookLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLayout();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initLayout() {
        app = (MyApplication) getApplication();

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton) findViewById(R.id.activity_login_with_facebook_button);
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                writeTokenToSharedPreferences(loginResult);
                postToBE(loginResult);
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "User canceled login");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    private void postToBE(LoginResult loginResult) {
        Profile profile = Profile.getCurrentProfile();

        // TODO: 24.03.2016 Configure profile pic dimensions
        Call<LoginPostBody> loginCall = app.getRestClient().getApiService().LOGIN_POST_BODY_CALL(profile.getFirstName(), profile.getLastName(), loginResult.getAccessToken().getToken().toString(), profile.getProfilePictureUri(200, 200).toString());
        loginCall.enqueue(new Callback<LoginPostBody>() {
            @Override
            public void onResponse(Response<LoginPostBody> response, Retrofit retrofit) {
                Log.i(TAG, "Login successful");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void writeTokenToSharedPreferences(LoginResult loginResult) {
        SharedPreferences.Editor editor = getSharedPreferences(MyApplication.SHARED_PREFERENCES_TAG, MODE_PRIVATE).edit();

        Profile profile = Profile.getCurrentProfile();

        Log.i(TAG, profile.getFirstName() + "  " + profile.getLastName() + "  " + profile.getProfilePictureUri(20, 20));

        editor.putString("token", loginResult.getAccessToken().getToken().toString());
        editor.putString("first_name", profile.getFirstName());
        editor.putString("last_name", profile.getLastName());
        editor.putString("profile_pic", profile.getProfilePictureUri(100, 100).toString());

        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
