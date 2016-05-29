package com.unibuc.communityhelpv3.activities;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.LoginListener;
import com.unibuc.communityhelpv3.pojos.interfaces.ProfileListener;
import com.unibuc.communityhelpv3.services.RegistrationIntentService;

public class LoginActivity extends AppCompatActivity implements LoginListener, ProfileListener {

    private final static String TAG = "LoginActivity";

    MyApplication app;

    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private LocalUserManager localUserManager;

    private NetworkManager networkManager;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
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
        preferenceManager.set_facebook_logged_in(true);
        preferenceManager.add_notification_unique_id(0);

        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.login(profile.getFirstName(), profile.getLastName(), loginResult.getAccessToken().getToken(), gcmToken, profile.getProfilePictureUri(200, 200).toString(), this);
    }

    private void writeTokenToSharedPreferences(LoginResult loginResult) {
        Profile profile = Profile.getCurrentProfile();

        Log.i(TAG, profile.getFirstName() + "  " + profile.getLastName() + "  " + profile.getProfilePictureUri(20, 20));

        ((MyApplication) getApplication()).getPrefManager().writeProfile(loginResult.getAccessToken().getToken().toString(), profile.getFirstName(), profile.getLastName(), profile.getProfilePictureUri(100, 100).toString());
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

        networkManager = NetworkManager.getInstance();
        networkManager.getProfile(userId, this);

        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();
        preferenceManager.add_current_user_id(userId);
        preferenceManager.set_facebook_logged_in(true);

        progressDialog.dismiss();

        Intent intent = new Intent(LoginActivity.this, PlacePickersActivity.class);
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

    @Override
    public void onProfileSuccess(UserGetBody.User response) {
        String resources = response.getResource_value();
        Log.d(TAG, response.getResource_value() + " login ");
        Log.d(TAG, resources + " login ");
        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();

        preferenceManager.set_user_resources(resources);
    }

    @Override
    public void onProfileFailed() {

    }
}
