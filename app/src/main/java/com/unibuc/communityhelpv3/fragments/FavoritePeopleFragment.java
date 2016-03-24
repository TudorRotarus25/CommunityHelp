package com.unibuc.communityhelpv3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.TaskAdapter;
import com.unibuc.communityhelpv3.pojos.TaskGetBody;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class FavoritePeopleFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskAdapter mAdapter;
    private ArrayList<TaskGetBody> tasksArrayList;
    //Context context = this;


    public FavoritePeopleFragment() {
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

        View v = inflater.inflate(R.layout.fragment_my_tasks, container, false);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycle_view);

        tasksArrayList = new ArrayList<>();

        //////de test
        TaskGetBody task;

        int i = 0;
        while(i != 100)
        {
            i++;
            task = new TaskGetBody("Task "+i);
            tasksArrayList.add(task);
        }

        ////

        mAdapter = new TaskAdapter(getContext(), tasksArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return v;
    }
}
