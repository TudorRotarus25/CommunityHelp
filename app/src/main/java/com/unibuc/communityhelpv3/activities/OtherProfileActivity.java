package com.unibuc.communityhelpv3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.utils.DownloadImageToImageView;

public class OtherProfileActivity extends AppCompatActivity {
    private static final String TAG = "OtherProfileActivity";
    private UserGetBody.User currentUser;
    private DownloadImageToImageView downloadImageToImageView;

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
            tvName.setText(currentUser.getFirst_name() + " " + currentUser.getLast_name());
            tvRank.setText(currentUser.getRank());
            sbRating.setProgress(Integer.parseInt(currentUser.getRating()));
            tvEmail.setText(currentUser.getEmail());
            tvPhone.setText(currentUser.getPhone_number());

            downloadImageToImageView.downloadImageToIV(currentUser.getProfile_pic(), profilePicture);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
