package com.unibuc.communityhelpv3.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.LocalUserManager;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.utils.DownloadImageToImageView;
import com.unibuc.communityhelpv3.managers.LocalImageManager;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.ProfileListener;

public class ProfileActivity extends AppCompatActivity implements ProfileListener {
    private static final String TAG = "ProfileActivity";

    private TextView name;
    private ImageView profilePicture;
    private TextView rank;
    private SeekBar rating;
    private TextView email;
    private TextView phone;

    private DownloadImageToImageView downloadImageToImageView;
    private LocalImageManager localImageManager;
    private NetworkManager networkManager;
    private LocalUserManager localUserManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        String userId = localUserManager.getLocalUserId();

        Log.i(TAG, "User id: " + userId );
        networkManager.getProfile(userId, this);
    }

    private void init(){
        name = (TextView) findViewById(R.id.name_textView);
        profilePicture = (ImageView) findViewById(R.id.profilePicture_ImageView);
        rank = (TextView) findViewById(R.id.rank_textView);
        rating = (SeekBar) findViewById(R.id.seekBar);
        email = (TextView) findViewById(R.id.emailTextView);
        phone = (TextView) findViewById(R.id.phoneTextView);

        localUserManager = LocalUserManager.getInstance(getApplicationContext());
        networkManager = NetworkManager.getInstance();
        downloadImageToImageView = new DownloadImageToImageView();
        localImageManager = new LocalImageManager(getApplicationContext());
    }

    @Override
    public void onProfileSuccess(UserGetBody.User response) {
        name.setText(response.getFirst_name() + " " + response.getLast_name());
        rank.setText(response.getRank());
        rating.setProgress(Integer.parseInt(response.getRating()));
        email.setText(response.getEmail());
        phone.setText(response.getPhone_number());

        if(response.getProfile_pic() != null) {
            setProfilePicture(response.getProfile_pic());
        }
    }

    @Override
    public void onProfileFailed() {

    }

    public void setProfilePicture(String profilePicUrl){
        String localPictureUrl = localImageManager.getProfilePictureUrl();
        if (!profilePicUrl.equals(localPictureUrl)){
            Log.e(TAG, "Set picture from SERVER");
            downloadImageToImageView.downloadImageToIV(profilePicUrl, profilePicture);
            localImageManager.storeProfilePictureUrl(profilePicUrl);
        }
        else{
            Log.e(TAG, "Set picture from LOCAL");
            Bitmap localProfilePictureBmp = LocalImageManager.retrieveProfilePicture();
            if(localProfilePictureBmp != null) {
                profilePicture.setImageBitmap(localProfilePictureBmp);
            }
        }
    }

}
