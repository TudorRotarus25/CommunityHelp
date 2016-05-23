package com.unibuc.communityhelpv3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TaskDetails;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.TaskListener;
import com.unibuc.communityhelpv3.utils.AppUtils;

public class TaskDetailsActivity extends AppCompatActivity implements TaskListener {
    private static final String TAG = "TaskDetailsActivity";
    private TasksGetBody.Task currentTask;
    private AccessToken accessToken;
    private NetworkManager networkManager;

    private String taskId;
    private int currentTaskId;

    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvEstimatedTime;
    private TextView tvDetails;
    private TextView tvUserame;
    private TextView tvRating;
    private TextView tvReward;
    private TextView tvPhone;
    private Button acceptTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_task_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getString("task_id");
        }

        init();
    }

    private void init(){
        accessToken = AccessToken.getCurrentAccessToken();
        currentTask = AppUtils.getCurrentTask(getApplicationContext());
        currentTaskId = getIntent().getIntExtra("task_id", 0);
        networkManager = NetworkManager.getInstance();

        tvTitle = (TextView) findViewById(R.id.layout_task_details_title_textView);
        tvDate = (TextView) findViewById(R.id.task_details_tvDate);
        tvTime = (TextView) findViewById(R.id.task_details_tvTime);
        tvEstimatedTime = (TextView) findViewById(R.id.layout_task_details_estimated_time_textView);
        tvDetails = (TextView) findViewById(R.id.layout_task_details_details_textVIew);
        tvUserame = (TextView) findViewById(R.id.layout_task_details_username_textView);
        tvRating = (TextView) findViewById(R.id.layout_task_details_rating_textView);
        tvReward = (TextView) findViewById(R.id.layout_task_details_reward_textView);
        tvPhone = (TextView) findViewById(R.id.layout_task_details_phone_textView);
        acceptTaskButton = (Button) findViewById(R.id.layout_task_details_accept_task_button);

        acceptTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkManager.acceptTask(accessToken.getToken(), currentTask.getId());
                finish();
            }
        });

        tvTitle.setText(currentTask.getTitle());

        Log.e("!!! id", currentTask.getId()+" " + currentTaskId);
        networkManager.getTask(19, TaskDetailsActivity.this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onGetTaskSucces(TaskDetails task) {
        Log.e("!!!", "SUCCESS");
        tvDate.setText(task.getCreated_at());
        tvEstimatedTime.setText(task.getTime_cost());
        tvDetails.setText(task.getDescription());
        tvUserame.setText(task.getOwner_id());
        tvReward.setText(task.getResource_cost());
    }

    @Override
    public void onGetTaskFailed() {

    }
}
