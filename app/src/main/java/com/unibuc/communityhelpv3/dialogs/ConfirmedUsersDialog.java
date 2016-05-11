package com.unibuc.communityhelpv3.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.ConfirmedUsersAdapter;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TasksGetParticipantsBody;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.TasksParticipantsListener;

import java.util.ArrayList;

/**
 * Created by Tudor on 11.05.2016.
 */
public class ConfirmedUsersDialog extends DialogFragment implements TasksParticipantsListener {

    private final String TAG = getClass().getSimpleName();

    private static final String BUNDLE_TASK_ID = "BUNDLE_TASK_ID";
    private static final int BUNDLE_TASK_DEFAULT_VALUE = -1;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ConfirmedUsersAdapter confirmedUsersAdapter;

    private int taskId;

    ArrayList<UserGetBody.User> confirmedUsers;

    private NetworkManager networkManager;

    public ConfirmedUsersDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_pending_users, container, false);

        TextView titleTextView = (TextView) v.findViewById(R.id.content_pending_users_title);
        titleTextView.setText("Confirmed Users");

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

        networkManager = NetworkManager.getInstance();
        confirmedUsers = new ArrayList<>();

        confirmedUsersAdapter = new ConfirmedUsersAdapter(getActivity(), confirmedUsers);
        recyclerView.setAdapter(confirmedUsersAdapter);

        getUsers();

        return v;
    }

    private void getUsers() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        swipeRefreshLayout.setRefreshing(true);

        networkManager.getTaskConfirmedUsers(accessToken.getToken(), taskId, this);
    }

    public static ConfirmedUsersDialog newInstance(int id) {
        ConfirmedUsersDialog frag = new ConfirmedUsersDialog();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_TASK_ID, id);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onGetTasksParticipantsSuccess(TasksGetParticipantsBody response) {

        swipeRefreshLayout.setRefreshing(false);

        if (response.getParticipants() != null) {
            confirmedUsers = response.getParticipants();
            confirmedUsersAdapter.setConfirmedUsersList(confirmedUsers);
            confirmedUsersAdapter.notifyDataSetChanged();
        } else {
            Log.e(TAG, "Empty response");
        }
    }

    @Override
    public void onGetTasksParticipantsFailed() {
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(getActivity(), "Failed to fetch participants", Toast.LENGTH_SHORT).show();
    }
}
