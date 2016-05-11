package com.unibuc.communityhelpv3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.UserGetBody;

import java.util.ArrayList;

/**
 * Created by Tudor on 11.05.2016.
 */
public class ConfirmedUsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
    ArrayList<UserGetBody.User> confirmedUsersList;

    public ConfirmedUsersAdapter(Context context, ArrayList<UserGetBody.User> confirmedUsersList) {
        this.context = context;
        this.confirmedUsersList = confirmedUsersList;
    }

    public ArrayList<UserGetBody.User> getConfirmedUsersList() {
        return confirmedUsersList;
    }

    public void setConfirmedUsersList(ArrayList<UserGetBody.User> confirmedUsersList) {
        this.confirmedUsersList = confirmedUsersList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_pending_users, parent, false);
        return new ConfirmedUserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserGetBody.User user = confirmedUsersList.get(position);
        ((ConfirmedUserViewHolder) holder).nameTextView.setText(user.getFirst_name() + " " + user.getLast_name());
        ((ConfirmedUserViewHolder) holder).levelTextView.setText(user.getRank());
    }

    @Override
    public int getItemCount() {
        return confirmedUsersList.size();
    }

    public class ConfirmedUserViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView nameTextView;
        TextView levelTextView;

        public ConfirmedUserViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.layout_pending_users_icon);
            nameTextView = (TextView) itemView.findViewById(R.id.layout_pending_users_name_textView);
            levelTextView = (TextView) itemView.findViewById(R.id.layout_pending_users_level_textView);
        }
    }
}
