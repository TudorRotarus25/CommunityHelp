package com.unibuc.communityhelpv3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.MyApplication;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.MyPreferenceManager;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TaskDetailsGetBody;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.GetOtherTaskDetailsListener;
import com.unibuc.communityhelpv3.utils.AppUtils;

public class TaskDetailsActivity extends AppCompatActivity implements GetOtherTaskDetailsListener{
    private static final String TAG = "TaskDetailsActivity";
    private TasksGetBody.Task currentTask;

    private String taskId;

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvEstimatedTime;
    private TextView tvDetails;
    private TextView tvUserame;
    private TextView tvRating;
    private TextView tvReward;
    private TextView tvPhone;
    private Button tvAcceptButton;


    private MyPreferenceManager preferenceManager;
    private NetworkManager networkManager;
    private AccessToken accessToken;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_task_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getString("task_id");
        }

        accessToken = AccessToken.getCurrentAccessToken();
        networkManager = NetworkManager.getInstance();
        MyPreferenceManager preferenceManager = MyApplication.getInstance().getPrefManager();
        userId = preferenceManager.get_current_user_id();

        if(accessToken != null) {
            Log.i(TAG, accessToken.getToken());
            networkManager.getOtherTaskDetails(accessToken.getToken(),taskId, TaskDetailsActivity.this);
        } else {
            Toast.makeText(TaskDetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No access token");
        }

        init();
    }

    private void init(){
        currentTask = AppUtils.getCurrentTask(getApplicationContext());

        tvTitle = (TextView) findViewById(R.id.layout_task_details_title_textView);
        tvDate = (TextView) findViewById(R.id.task_details_tvDate);
        tvTime = (TextView) findViewById(R.id.task_details_tvTime);
        tvEstimatedTime = (TextView) findViewById(R.id.layout_task_details_estimated_time_textView);
        tvDetails = (TextView) findViewById(R.id.layout_task_details_details_textVIew);
        tvUserame = (TextView) findViewById(R.id.layout_task_details_username_textView);
        tvRating = (TextView) findViewById(R.id.layout_task_details_rating_textView);
        tvReward = (TextView) findViewById(R.id.layout_task_details_reward_textView);
        tvPhone = (TextView) findViewById(R.id.layout_task_details_phone_textView);
        tvAcceptButton = (Button) findViewById(R.id.layout_task_details_accept_task_button);

        tvTitle.setText(currentTask.getTitle());
//        tvDate.setText(currentTask.getCreated_at());
//        tvEstimatedTime.setText(currentTask.getTime_cost());
//        tvDetails.setText(currentTask.getDescription());
//        tvUserame.setText(currentTask.getOwner_id());
//        tvReward.setText(currentTask.getResource_cost());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //// TODO: 19-May-16
    @Override
    public void onGetOtherTaskDetailsSuccess(TaskDetailsGetBody response) {
        currentTask = response.getTask();
    }

    @Override
    public void onGetOtherTaskDetailsFailed() {
        Toast.makeText(this, "Failed to fetch details!", Toast.LENGTH_SHORT).show();
    }
}
