package com.unibuc.communityhelpv3.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.unibuc.communityhelpv3.dialogs.ConfirmedUsersDialog;
import com.unibuc.communityhelpv3.dialogs.PendingUsersDialog;
import com.unibuc.communityhelpv3.managers.MyPreferenceManager;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TaskDetailsGetBody;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.GetMyTaskDetailsListener;
import com.unibuc.communityhelpv3.utils.AppUtils;

public class MyTaskDetailsActivity extends AppCompatActivity implements GetMyTaskDetailsListener {
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

    private MyPreferenceManager preferenceManager;
    private NetworkManager networkManager;
    private AccessToken accessToken;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_task_details);

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
            networkManager.getMyTaskDetails(accessToken.getToken(), taskId, MyTaskDetailsActivity.this);
        } else {
            Toast.makeText(MyTaskDetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No access token");
        }

        init();
    }

    private void populateTask()
    {
        Log.d(TAG, currentTask.getTitle());
        Log.d(TAG, currentTask.getDescription());
        Log.d(TAG, currentTask.getCreated_at());
        Log.d(TAG, currentTask.getTime_cost()+"");
        Log.d(TAG, currentTask.getName());
        Log.d(TAG, currentTask.getResource_cost()+"");

        Log.d(TAG, currentTask.getRating()+"");
        tvTitle.setText(currentTask.getTitle());
        tvDate.setText(currentTask.getCreated_at());

        String aux = currentTask.getTime_cost()+"";
        tvEstimatedTime.setText(aux);

        tvDetails.setText(currentTask.getDescription());
        tvUserame.setText(currentTask.getName());
        aux = currentTask.getResource_cost() + "";
        tvReward.setText(aux);
        tvRating.setText(currentTask.getRating());
    }

    private void init(){
        currentTask = AppUtils.getCurrentTask(getApplicationContext());

        tvTitle = (TextView) findViewById(R.id.layout_my_task_details_title_textView);
        tvDate = (TextView) findViewById(R.id.my_task_details_tvDate);
        //tvTime = (TextView) findViewById(R.id.my_task_details_tvTime);
        tvEstimatedTime = (TextView) findViewById(R.id.layout_my_task_details_estimated_time_textView);
        tvDetails = (TextView) findViewById(R.id.layout_my_task_details_details_textVIew);
        tvUserame = (TextView) findViewById(R.id.layout_my_task_details_username_textView);
        tvRating = (TextView) findViewById(R.id.layout_my_task_details_rating_textView);
        tvReward = (TextView) findViewById(R.id.layout_my_task_details_reward_textView);

        pendingButton = (Button) findViewById(R.id.layout_my_task_details_pending_button);
        confirmedButton = (Button) findViewById(R.id.layout_my_task_details_confirmed_button);

        pendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingUsersDialog pendingDialog = PendingUsersDialog.newInstance(currentTask.getId());
                pendingDialog.show(MyTaskDetailsActivity.this.getFragmentManager(), TAG);
            }
        });

        confirmedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmedUsersDialog confirmedDialog = ConfirmedUsersDialog.newInstance(currentTask.getId());
                confirmedDialog.show(MyTaskDetailsActivity.this.getFragmentManager(), TAG);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //// TODO: 19-May-16
    @Override
    public void onGetMyTaskDetailsSuccess(TaskDetailsGetBody response) {
        currentTask = response.getTask();
        populateTask();
    }

    @Override
    public void onGetMyTaskDetailsFailed() {
        Toast.makeText(this, "Failed to fetch details!", Toast.LENGTH_SHORT).show();
    }
}
