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

import com.facebook.AccessToken;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.MyTasksAdapter;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.MyTasksListener;

import java.util.ArrayList;


public class MyTasksFragment extends Fragment implements MyTasksListener{

    private final String TAG = "MyTasksFragment";

    private RecyclerView recyclerView;
    private MyTasksAdapter mAdapter;
    private ArrayList<TasksGetBody.Task> tasksArrayList;
    //Context context = this;


    public MyTasksFragment() {
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


        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)container.findViewById(R.id.content_my_tasks_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateLayouts();
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

        tasksArrayList = new ArrayList<>();

        mAdapter = new MyTasksAdapter(getContext(), tasksArrayList, TAG);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        populateLayouts();

        return v;
    }

    private void populateLayouts() {

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken != null) {
            Log.i(TAG, accessToken.getToken());
            NetworkManager networkManager = NetworkManager.getInstance();
            networkManager.getMyTasks(accessToken.getToken(), this);
        } else {
            Log.e(TAG, "No access token");
        }

    }


    @Override
    public void onGetMyTasksSuccess(TasksGetBody response) {


        tasksArrayList = response.getTasks();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetMyTasksFailed() {

    }
}
