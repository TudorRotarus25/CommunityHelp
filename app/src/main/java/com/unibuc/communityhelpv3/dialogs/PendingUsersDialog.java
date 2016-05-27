package com.unibuc.communityhelpv3.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.activities.OtherProfileActivity;
import com.unibuc.communityhelpv3.adapters.PendingUsersAdapter;
import com.unibuc.communityhelpv3.adapters.interfaces.OnPendingUserClickedInterface;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TasksGetParticipantsBody;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.TasksParticipantsListener;

import java.util.ArrayList;

/**
 * Created by Tudor on 09.05.2016.
 */
public class PendingUsersDialog extends DialogFragment implements TasksParticipantsListener, OnPendingUserClickedInterface {

    private final String TAG = getClass().getSimpleName();

    private static final String BUNDLE_TASK_ID = "BUNDLE_TASK_ID";
    private static final int BUNDLE_TASK_DEFAULT_VALUE = -1;

    private int taskId;
    private int firstTimeGettingUsers = 1;

    ArrayList<UserGetBody.User> pendingUsers;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PendingUsersAdapter pendingUsersAdapter;

    private NetworkManager networkManager;

    public PendingUsersDialog() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (firstTimeGettingUsers == 0)
            getUsers();
        firstTimeGettingUsers = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_pending_users, container, false);

        networkManager = NetworkManager.getInstance();

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.content_pending_users_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.content_pending_users_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        taskId = getArguments().getInt(BUNDLE_TASK_ID, BUNDLE_TASK_DEFAULT_VALUE);

        pendingUsers = new ArrayList<>();

        pendingUsersAdapter = new PendingUsersAdapter(getActivity(), pendingUsers, this);
        recyclerView.setAdapter(pendingUsersAdapter);

        getUsers();

        return v;
    }

    private void getUsers(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        swipeRefreshLayout.setRefreshing(true);

        networkManager.getTaskPendingUsers(accessToken.getToken(), taskId, this);
    }

    public static PendingUsersDialog newInstance(int id) {
        PendingUsersDialog frag = new PendingUsersDialog();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_TASK_ID, id);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onGetTasksParticipantsSuccess(TasksGetParticipantsBody response) {

        swipeRefreshLayout.setRefreshing(false);

        if (response.getParticipants() != null) {

            Log.i(TAG, "Response not empty, size: " + response.getParticipants().size());

            pendingUsers = response.getParticipants();

            pendingUsersAdapter.setPendingUsersList(pendingUsers);
            pendingUsersAdapter.notifyDataSetChanged();
        } else {
            Log.e(TAG, "Empty response");
        }
    }

    @Override
    public void onGetTasksParticipantsFailed() {
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(getActivity(), "Failed to fetch participants", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPendingUserClicked(UserGetBody.User user) {
        Intent i = new Intent(getActivity(), OtherProfileActivity.class);
        i.putExtra("user", user);
        i.putExtra("taskId", taskId);
        startActivity(i);
    }
}
