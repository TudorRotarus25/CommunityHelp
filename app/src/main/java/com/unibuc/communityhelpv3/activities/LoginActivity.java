package com.unibuc.communityhelpv3.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.unibuc.communityhelpv3.MyApplication;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.LocalUserManager;
import com.unibuc.communityhelpv3.managers.MyPreferenceManager;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.LoginPostBody;
import com.unibuc.communityhelpv3.pojos.interfaces.LoginListener;
import com.unibuc.communityhelpv3.services.RegistrationIntentService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements LoginListener{

    private final static String TAG = "LoginActivity";

    MyApplication app;

    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private LocalUserManager localUserManager;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

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
        localUserManager = LocalUserManager.getInstance(getApplicationContext());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait while loading");


        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton) findViewById(R.id.activity_login_with_facebook_button);
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                writeTokenToSharedPreferences(loginResult);
                postToBE(loginResult);
                progressDialog.show();
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
        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();
        String gcmToken = preferenceManager.get_gcm_token();
        preferenceManager.add_notification_unique_id(0);

        // TODO: 24.03.2016 Configure profile pic dimensions
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.login(profile.getFirstName(), profile.getLastName(), loginResult.getAccessToken().getToken(), gcmToken, profile.getProfilePictureUri(200, 200).toString(), this);
    }

    private void writeTokenToSharedPreferences(LoginResult loginResult) {
        SharedPreferences.Editor editor = getSharedPreferences(MyApplication.SHARED_PREFERENCES_TAG, MODE_PRIVATE).edit();

        Profile profile = Profile.getCurrentProfile();

        Log.i(TAG, profile.getFirstName() + "  " + profile.getLastName() + "  " + profile.getProfilePictureUri(20, 20));

        editor.putString("token", loginResult.getAccessToken().getToken().toString());
        editor.putString("first_name", profile.getFirstName());
        editor.putString("last_name", profile.getLastName());
        Log.i(TAG, profile.getProfilePictureUri(100, 100).toString());
        editor.putString("profile_pic", profile.getProfilePictureUri(100, 100).toString());

        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLoginSuccess(LoginPostBody response) {
        String userId = response.getUser_id();
        Log.d(TAG, userId);
        localUserManager.storeLocalUserId(userId);

        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();
        preferenceManager.add_current_user_id(userId);

        progressDialog.dismiss();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login failed, please try again", Toast.LENGTH_SHORT).show();
    }

    //checks if Google Play Services is installed and updated
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, 9000).show();
            } else {
                Log.i("Home Activity", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
