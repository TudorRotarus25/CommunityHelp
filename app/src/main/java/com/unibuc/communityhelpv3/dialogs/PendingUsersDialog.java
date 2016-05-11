package com.unibuc.communityhelpv3.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.PendingUsersAdapter;
import com.unibuc.communityhelpv3.pojos.UserGetBody;

import java.util.ArrayList;

/**
 * Created by Tudor on 09.05.2016.
 */
public class PendingUsersDialog extends DialogFragment {

    private static final String BUNDLE_TASK_ID = "BUNDLE_TASK_ID";
    private static final int BUNDLE_TASK_DEFAULT_VALUE = -1;

    private RecyclerView recyclerView;
    private PendingUsersAdapter pendingUsersAdapter;

    public PendingUsersDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_pending_users, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.content_pending_users_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        int taskId = getArguments().getInt(BUNDLE_TASK_ID, BUNDLE_TASK_DEFAULT_VALUE);

        ArrayList<UserGetBody.User> testUsers = new ArrayList<>();
        UserGetBody.User newUser = new UserGetBody().new User();
        newUser.setFirst_name("Tudor");
        newUser.setLast_name("Rotarus");
        newUser.setRank("3");
        testUsers.add(newUser);

        pendingUsersAdapter = new PendingUsersAdapter(getActivity(), testUsers);
        recyclerView.setAdapter(pendingUsersAdapter);

        return v;
    }

    public static PendingUsersDialog newInstance(int id) {
        PendingUsersDialog frag = new PendingUsersDialog();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_TASK_ID, id);
        frag.setArguments(args);
        return frag;
    }
}
