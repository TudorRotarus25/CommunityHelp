package com.unibuc.communityhelpv3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.dialogs.ConfirmedUsersDialog;
import com.unibuc.communityhelpv3.dialogs.PendingUsersDialog;
import com.unibuc.communityhelpv3.pojos.TaskDetailsGetBody;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.TaskListener;
import com.unibuc.communityhelpv3.utils.AppUtils;
import com.unibuc.communityhelpv3.utils.DownloadImageToImageView;

public class MyTaskDetailsActivity extends AppCompatActivity implements TaskListener {
    private static final String TAG = "MyTaskDetailsActivity";
    private TasksGetBody.Task currentTask;
    private NetworkManager networkManager;
    DownloadImageToImageView downloadImageToImageView;

    private String taskId;
    private String currentTaskId;

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvEstimatedTime;
    private TextView tvDetails;
    private TextView tvUserame;
    private TextView tvRating;
    private TextView tvReward;
    private ImageView ivCategory;
    private Button pendingButton;
    private Button confirmedButton;
    private Button startButton;
    private Button deleteButton;
    private Button finishButton;

    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_task_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getString("task_id");
        }

        init();
    }

    private void init(){
        accessToken = AccessToken.getCurrentAccessToken();
        currentTask = AppUtils.getCurrentTask(getApplicationContext());
        networkManager = NetworkManager.getInstance();
        currentTaskId = getIntent().getStringExtra("task_id");

        downloadImageToImageView = new DownloadImageToImageView();

        ivCategory = (ImageView) findViewById(R.id.layout_my_task_details_image_imageView);
        tvTitle = (TextView) findViewById(R.id.layout_my_task_details_title_textView);
        tvDate = (TextView) findViewById(R.id.my_task_details_tvDate);
        tvTime = (TextView) findViewById(R.id.my_task_details_tvTime);
        tvEstimatedTime = (TextView) findViewById(R.id.layout_my_task_details_estimated_time_textView);
        tvDetails = (TextView) findViewById(R.id.layout_my_task_details_details_textVIew);
        tvUserame = (TextView) findViewById(R.id.layout_my_task_details_username_textView);
        tvRating = (TextView) findViewById(R.id.layout_my_task_details_rating_textView);
        tvReward = (TextView) findViewById(R.id.layout_my_task_details_reward_textView);
        pendingButton = (Button) findViewById(R.id.layout_my_task_details_pending_button);
        confirmedButton = (Button) findViewById(R.id.layout_my_task_details_confirmed_button);
        startButton = (Button) findViewById(R.id.layout_my_task_details_start_button);
        finishButton = (Button) findViewById(R.id.layout_my_task_details_finish_button);
        deleteButton = (Button) findViewById(R.id.layout_my_task_details_delete_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, currentTask.getParticipants_number() + "");
                Log.d(TAG, currentTaskId + "");
                Log.d(TAG, accessToken.getToken() + "");
                networkManager.startTask(accessToken.getToken(), Integer.parseInt(currentTaskId));
                startButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                finishButton.setVisibility(View.VISIBLE);
                /*
                if(currentTask.getParticipants_number() <= 0)
                {
                    Toast.makeText(MyTaskDetailsActivity.this, "No people confirmed!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(TAG, currentTaskId + "");
                    Log.d(TAG, accessToken.getToken() + "");
                    networkManager.startTask(accessToken.getToken(), Integer.parseInt(currentTaskId));
                    startButton.setVisibility(View.GONE);
                    deleteButton.setVisibility(View.GONE);
                    finishButton.setVisibility(View.VISIBLE);
                }
                */
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, currentTaskId + "");
                Log.d(TAG, accessToken.getToken() + "");
                networkManager.finishTask(accessToken.getToken(), Integer.parseInt(currentTaskId));

                Intent intent = new Intent(MyTaskDetailsActivity.this, RateActivity.class);
                intent.putExtra("BUNDLE_KEY_TASK_ID", currentTaskId);
                startActivity(intent);

                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, currentTaskId + "");
                Log.d(TAG, accessToken.getToken() + "");
                networkManager.deleteTask(accessToken.getToken(), Integer.parseInt(currentTaskId));

                finish();
            }
        });

        tvTitle.setText(currentTask.getTitle());
//        tvDate.setText(currentTask.getCreated_at());
//        tvEstimatedTime.setText(currentTask.getTime_cost());
//        tvDetails.setText(currentTask.getDescription());
//        tvUserame.setText(currentTask.getOwner_id());
//        tvReward.setText(currentTask.getResource_cost());

        Log.e("!!! id", currentTask.getId()+" " + currentTaskId);
        networkManager.getTask(Integer.parseInt(currentTaskId), MyTaskDetailsActivity.this);
        pendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingUsersDialog pendingDialog = PendingUsersDialog.newInstance(Integer.parseInt(currentTask.getId()));
                pendingDialog.show(MyTaskDetailsActivity.this.getFragmentManager(), TAG);
            }
        });

        confirmedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmedUsersDialog confirmedDialog = ConfirmedUsersDialog.newInstance(Integer.parseInt(currentTask.getId()));
                confirmedDialog.show(MyTaskDetailsActivity.this.getFragmentManager(), TAG);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onGetTaskSucces(TaskDetailsGetBody taskResponse) {
        TasksGetBody.Task task = taskResponse.getTask();

        Log.e("!!!", "SUCCESS" + task.getTitle());
        tvDate.setText(task.getCreated_at());
        tvEstimatedTime.setText(task.getTime_cost());
        tvDetails.setText(task.getDescription());
        tvUserame.setText(task.getName());
        tvReward.setText(task.getResource_cost());

        downloadImageToImageView.downloadImageToIV(task.getCategory_picture(),ivCategory);

        if (task.getStatus().equals("0")){
            deleteButton.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.VISIBLE);
        }
        else if (task.getStatus().equals("1")){
            deleteButton.setVisibility(View.GONE);
            finishButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetTaskFailed() {

    }
}
