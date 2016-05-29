package com.unibuc.communityhelpv3.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TasksGetParticipantsBody;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.RateParticipantsListener;
import com.unibuc.communityhelpv3.pojos.interfaces.TasksParticipantsListener;
import com.unibuc.communityhelpv3.pojos.requests.RatingPostBody;
import com.unibuc.communityhelpv3.pojos.requests.RatingPostBody.Ratings;

import java.util.ArrayList;

public class RateActivity extends AppCompatActivity implements TasksParticipantsListener, RateParticipantsListener {

    private final String TAG = getClass().getSimpleName();

    public final static String BUNDLE_KEY_TASK_ID = "BUNDLE_KEY_TASK_ID";

    private TextView nameTextView;
    private RatingBar ratingBar;
    private Button previousButton;
    private Button nextButton;

    private ProgressDialog progressDialog;

    private RatingPostBody ratingPostBody;
    private int position = 0;

    private ArrayList<UserGetBody.User> participants;

    private NetworkManager networkManager;
    private String fbToken;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout(savedInstanceState);
    }

    private void initLayout(Bundle bundle) {
        setContentView(R.layout.activity_rate);

        nameTextView = (TextView) findViewById(R.id.activity_rate_name_textView);
        ratingBar = (RatingBar) findViewById(R.id.activity_rate_rating);
        previousButton = (Button) findViewById(R.id.activity_rate_previous_button);
        nextButton = (Button) findViewById(R.id.activity_rate_next_button);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait while loading...");
        progressDialog.show();

        networkManager = NetworkManager.getInstance();

        ratingPostBody = new RatingPostBody();

        String taskIdString = getIntent().getStringExtra(BUNDLE_KEY_TASK_ID);

        if(taskIdString != null)
            taskId = Integer.parseInt(taskIdString);
        else
            taskId = -1;

        fbToken = AccessToken.getCurrentAccessToken().getToken();

        if (taskId == -1) {
            Log.e(TAG, "task id is null");
            Toast.makeText(this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        ratingPostBody.setTaskId(taskId);
        ratingPostBody.setFacebookToken(fbToken);

        Log.d(TAG,fbToken);
        Log.d(TAG, taskId+"");
        networkManager.getTaskConfirmedUsers(fbToken, taskId, this);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingPostBody.removeRating(position);
                position--;
                changeLayout();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ratings newRating = new RatingPostBody().new Ratings(participants.get(position).getId(), ratingBar.getRating());
                ratingPostBody.addRating(newRating);
                if (position == participants.size() - 1) {
                    Log.d(TAG, ratingPostBody.getFacebookToken() + " " + ratingPostBody.getTaskId());
                    ArrayList<Ratings> ratings = ratingPostBody.getRatings();
                    for(int i = 0 ; i < ratings.size() ; i++)
                    {
                        Ratings r = ratings.get(i);
                        Log.d(TAG, r.getUserId() + " - " + r.getRating());
                    }

                    networkManager.rateParticipants(ratingPostBody, RateActivity.this);
                    progressDialog.show();
                } else {
                    position++;
                    changeLayout();
                }
            }
        });
    }

    private void changeLayout() {
        if (position == 0) {
            previousButton.setVisibility(View.INVISIBLE);
        } else {
            previousButton.setVisibility(View.VISIBLE);
        }
        if (position == participants.size()-1) {
            nextButton.setText("Finish");
        }
            nameTextView.setText(String.format("%s %s", participants.get(position).getFirst_name(), participants.get(position).getLast_name()));
        ratingBar.setRating(0);
    }

    @Override
    public void onGetTasksParticipantsSuccess(TasksGetParticipantsBody response) {
        participants = response.getParticipants();
        changeLayout();
        progressDialog.dismiss();
    }

    @Override
    public void onGetTasksParticipantsFailed() {
        progressDialog.dismiss();
        Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRateParticipantsSuccess() {
        progressDialog.dismiss();
        networkManager.deleteTask(fbToken, taskId);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onRateParticipantsFailed() {
        progressDialog.dismiss();
        Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
    }
}
