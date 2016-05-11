package com.unibuc.communityhelpv3.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.OtherTasksAdapter;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.TasksListener;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class OtherTasksFragment extends Fragment implements TasksListener {

    private final String TAG = "OtherTasksFragment";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private OtherTasksAdapter mAdapter;
    private ArrayList<TasksGetBody.Task> tasksArrayList;

    private NetworkManager networkManager;

    public OtherTasksFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.layout_fragment_tasks, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.content_my_tasks_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateLayout();
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);
        networkManager = NetworkManager.getInstance();
        tasksArrayList = new ArrayList<>();

        mAdapter = new OtherTasksAdapter(getContext(), tasksArrayList, TAG);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        populateLayout();

        return v;
    }

    public void populateLayout() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken != null) {
            Log.i(TAG, accessToken.getToken());
            networkManager.getOtherPeopleTasks(accessToken.getToken(), this);
        } else {
            Log.e(TAG, "No access token");
        }
    }

    @Override
    public void onGetMyTasksSuccess(TasksGetBody response) {
        swipeRefreshLayout.setRefreshing(false);

        if(response.getTasks() != null) {
            tasksArrayList = response.getTasks();
            mAdapter.setTasksArrayList(tasksArrayList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetMyTasksFailed() {
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(getActivity(), "Failed to fetch tasks", Toast.LENGTH_SHORT).show();
    }
}
