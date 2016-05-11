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
 * Created by Serban Theodor on 17-Mar-16.
 */
public class FavoritePeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String fragment_type;
    ArrayList<UserGetBody.User> userArrayList;

    public FavoritePeopleAdapter(Context mContext, ArrayList<UserGetBody.User> userArrayList, String fragment_type) {
        this.context = mContext;
        this.userArrayList = userArrayList;
        this.fragment_type = fragment_type;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_favorite_people, parent, false);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserGetBody.User user = userArrayList.get(position);
        ((FavoriteViewHolder) holder).fav_first_name.setText(user.getFirst_name());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    public class FavoriteViewHolder extends RecyclerView.ViewHolder {


        //favorite people
        TextView fav_first_name, fav_last_name, other_task_date_added, other_task_user,
                other_no_users, other_task_reward_info;
        ImageView other_task_image_url;

        public FavoriteViewHolder(View view) {
            super(view);
            fav_first_name = (TextView) itemView.findViewById(R.id.layout_pending_users_name_textView);

        }

    }
}
