package com.unibuc.communityhelpv3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.utils.DownloadImageToImageView;

public class OtherProfileActivity extends AppCompatActivity {
    private static final String TAG = "OtherProfileActivity";
    private UserGetBody.User currentUser;
    private DownloadImageToImageView downloadImageToImageView;
    private NetworkManager networkManager;
    private AccessToken accessToken;
    private int taskId;

    private TextView tvName;
    private ImageView profilePicture;
    private TextView tvRank;
    private SeekBar sbRating;
    private TextView tvEmail;
    private TextView tvPhone;
    private Button declineButton;
    private Button acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        init();
    }

    private void init(){
        accessToken = AccessToken.getCurrentAccessToken();
        networkManager = NetworkManager.getInstance();
        currentUser = (UserGetBody.User) getIntent().getSerializableExtra("user");
        taskId = getIntent().getIntExtra("taskId", taskId);
        Log.e("!!!", currentUser.getFirst_name());

        downloadImageToImageView = new DownloadImageToImageView();

        tvName = (TextView) findViewById(R.id.name_textView);
        profilePicture = (ImageView) findViewById(R.id.profilePicture_ImageView);
        tvRank = (TextView) findViewById(R.id.rank_textView);
        sbRating = (SeekBar) findViewById(R.id.seekBar);
        tvEmail = (TextView) findViewById(R.id.emailTextView);
        tvPhone = (TextView) findViewById(R.id.phoneTextView);
        declineButton = (Button) findViewById(R.id.declinewButton);
        acceptButton = (Button) findViewById(R.id.acceptButton);

        if (currentUser != null){
            if (currentUser.getFirst_name() != null && currentUser.getLast_name() != null)
                tvName.setText(currentUser.getFirst_name() + " " + currentUser.getLast_name());
            if (currentUser.getRank() != null)
                tvRank.setText(currentUser.getRank());
            if (currentUser.getRating() != null)
                sbRating.setProgress(Integer.parseInt(currentUser.getRating()));
            if (currentUser.getEmail() != null)
                tvEmail.setText(currentUser.getEmail());
            if (currentUser.getPhone_number() != null)
                tvPhone.setText(currentUser.getPhone_number());

            downloadImageToImageView.downloadImageToIV(currentUser.getProfile_pic(), profilePicture);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("confirm user", accessToken.getToken() + " " + currentUser.getId()+ " " + taskId);
                    networkManager.confirmParticipant(accessToken.getToken(), currentUser.getId(), taskId);
                    finish();
                }
            });

            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    networkManager.declineParticipant(accessToken.getToken(), currentUser.getId(), taskId);
                    finish();
                }
            });
        }
    }
}
