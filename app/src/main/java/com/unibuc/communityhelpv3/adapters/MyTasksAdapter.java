package com.unibuc.communityhelpv3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.TaskGetBody;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class MyTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String fragment_type;
    ArrayList<TaskGetBody> tasksArrayList;

    public MyTasksAdapter(Context mContext, ArrayList<TaskGetBody> tasksArrayList, String fragment_type) {
        this.context = mContext;
        this.tasksArrayList = tasksArrayList;
        this.fragment_type = fragment_type;

        //Log.d("DEBUG ", "ADAPTER CONSTRUCTOR");
        //Log.d("DEBUG ", getItemCount()+"");

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_my_task, parent, false);
        return new MyTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskGetBody task = tasksArrayList.get(position);
        ((MyTaskViewHolder) holder).my_task_title.setText(task.getTask_title());
        ((MyTaskViewHolder) holder).my_task_description.setText(task.getTask_desription());
        ((MyTaskViewHolder) holder).my_task_reward_info.setText(task.getTask_reward_info());
        //Log.d("DEBUG ", "TASK ADAPTER BIND VIEW HOLDER");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tasksArrayList.size();
    }


    public class MyTaskViewHolder extends RecyclerView.ViewHolder {

        //my tasks
        TextView my_task_title, my_task_time, my_task_date_added, my_task_user,
                my_no_users, my_task_reward_info, my_task_description;
        ImageView my_task_image_url;

        //other tasks
        TextView other_task_title, other_task_time, other_task_date_added, other_task_user,
                other_no_users, other_task_reward_info;
        ImageView other_task_image_url;

        //favorite people
        TextView fav_first_name, fav_last_name, fav_rating, fav_rank, fav_phone_number, fav_email;
        ImageView fav_profile_pic;

        public MyTaskViewHolder(View view) {
            super(view);
            my_task_title = (TextView) itemView.findViewById(R.id.layout_my_task_title_textView);
            my_task_description = (TextView) itemView.findViewById(R.id.layout_my_task_description_textView);
            my_task_reward_info = (TextView) itemView.findViewById(R.id.layout_my_task_stars_textView);
            my_task_image_url = (ImageView) itemView.findViewById(R.id.layout_my_task_icon_imageView);
        }

    }
}
