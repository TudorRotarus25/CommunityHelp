package com.unibuc.communityhelpv3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.adapters.interfaces.OnPendingUserClickedInterface;
import com.unibuc.communityhelpv3.pojos.UserGetBody;

import java.util.ArrayList;

/**
 * Created by Tudor on 09.05.2016.
 */
public class PendingUsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<UserGetBody.User> pendingUsersList;
    private OnPendingUserClickedInterface onPendingUserClickedInterface;

    public PendingUsersAdapter(Context context, ArrayList<UserGetBody.User> pendingUsersList,
                               OnPendingUserClickedInterface onPendingUserClickedInterface) {
        this.context = context;
        this.pendingUsersList = pendingUsersList;
        this.onPendingUserClickedInterface = onPendingUserClickedInterface;
    }

    public ArrayList<UserGetBody.User> getPendingUsersList() {
        return pendingUsersList;
    }

    public void setPendingUsersList(ArrayList<UserGetBody.User> pendingUsersList) {
        this.pendingUsersList = pendingUsersList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_pending_users, parent, false);
        return new PendingUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final UserGetBody.User user = pendingUsersList.get(position);
        ((PendingUserViewHolder) holder).nameTextView.setText(user.getFirst_name() + " " + user.getLast_name());
        ((PendingUserViewHolder) holder).levelTextView.setText(user.getRank());

        ((PendingUserViewHolder) holder).mainContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPendingUserClickedInterface.onPendingUserClicked(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendingUsersList.size();
    }

    public class PendingUserViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView nameTextView;
        TextView levelTextView;
        RelativeLayout mainContent;

        public PendingUserViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.layout_pending_users_icon);
            nameTextView = (TextView) itemView.findViewById(R.id.layout_pending_users_name_textView);
            levelTextView = (TextView) itemView.findViewById(R.id.layout_pending_users_level_textView);
            mainContent = (RelativeLayout) itemView.findViewById(R.id.pending_user_mainContent);
        }
    }
}
