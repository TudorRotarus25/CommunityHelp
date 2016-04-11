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
import com.unibuc.communityhelpv3.adapters.FavoritePeopleAdapter;
import com.unibuc.communityhelpv3.adapters.MyTasksAdapter;
import com.unibuc.communityhelpv3.pojos.TaskGetBody;
import com.unibuc.communityhelpv3.pojos.UserGetBody;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class FavoritePeopleFragment extends Fragment {

    private final String TAG = "FavoritePeopleFragment";

    private RecyclerView recyclerView;
    private FavoritePeopleAdapter mAdapter;
    private ArrayList<UserGetBody.User> usersArrayList;
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

        View v = inflater.inflate(R.layout.layout_fragment_tasks, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

        usersArrayList = new ArrayList<>();

        //////de test
        UserGetBody user;
        int i = 0;
        while(i != 40)
        {
            user = new UserGetBody();
            usersArrayList.add(user.getProfile());
            i++;
        }

        mAdapter = new FavoritePeopleAdapter(getContext(), usersArrayList, TAG);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return v;
    }
}
