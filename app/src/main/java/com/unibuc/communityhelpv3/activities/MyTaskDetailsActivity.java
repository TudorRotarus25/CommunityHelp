package com.unibuc.communityhelpv3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.utils.AppUtils;

public class MyTaskDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MyTaskDetailsActivity";
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
    private Button pendingButton;
    private Button confirmedButton;

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
        currentTask = AppUtils.getCurrentTask(getApplicationContext());

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
}
