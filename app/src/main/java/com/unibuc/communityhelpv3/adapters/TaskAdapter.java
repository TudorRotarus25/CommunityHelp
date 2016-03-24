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
public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String fragment_type;
    ArrayList<TaskGetBody> tasksArrayList;

    public TaskAdapter(Context mContext, ArrayList<TaskGetBody> tasksArrayList, String fragment_type) {
        this.context = mContext;
        this.tasksArrayList = tasksArrayList;
        this.fragment_type = fragment_type;

        //Log.d("DEBUG ", "ADAPTER CONSTRUCTOR");
        //Log.d("DEBUG ", getItemCount()+"");

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        //Log.d("DEBUG ", "TASK ADAPTER ITEM VIEW");

        if(fragment_type.equals("MyTasksFragment")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_my_task, parent, false);
        }

        if(fragment_type.equals("OtherTasksFragment")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_other_tasks, parent, false);
        }
        else
        {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_favorite_people, parent, false);
        }



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskGetBody task = tasksArrayList.get(position);
        ((ViewHolder) holder).other_task_title.setText(task.getTitle());
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        //my tasks
        TextView my_task_title, my_task_time, my_task_date_added, my_task_user,
                my_no_users, my_task_reward_info;
        ImageView my_task_image_url;

        //other tasks
        TextView other_task_title, other_task_time, other_task_date_added, other_task_user,
                other_no_users, other_task_reward_info;
        ImageView other_task_image_url;

        //favorite people
        TextView fav_first_name, fav_last_name, fav_rating, fav_rank, fav_phone_number, fav_email;
        ImageView fav_profile_pic;

        public ViewHolder(View view) {
            super(view);

            switch (view.getId()) {
                case R.layout.layout_my_task:
                    my_task_title = (TextView) itemView.findViewById(R.id.task_title);
                    my_task_time = (TextView) itemView.findViewById(R.id.task_time);
                    my_task_date_added = (TextView) itemView.findViewById(R.id.task_date_added);
                    my_task_user = (TextView) itemView.findViewById(R.id.user);
                    my_no_users = (TextView) itemView.findViewById(R.id.no_users);
                    my_task_reward_info = (TextView) itemView.findViewById(R.id.task_reward_info);
                    my_task_image_url = (ImageView) itemView.findViewById(R.id.task_image);
                    break;
                case R.layout.layout_other_tasks:
                    other_task_title = (TextView) itemView.findViewById(R.id.task_title);
                    other_task_time = (TextView) itemView.findViewById(R.id.task_time);
                    other_task_date_added = (TextView) itemView.findViewById(R.id.task_date_added);
                    other_task_user = (TextView) itemView.findViewById(R.id.user);
                    other_no_users = (TextView) itemView.findViewById(R.id.no_users);
                    other_task_reward_info = (TextView) itemView.findViewById(R.id.task_reward_info);
                    other_task_image_url = (ImageView) itemView.findViewById(R.id.task_image);
                    break;
                case R.layout.layout_favorite_people:
                    fav_first_name = (TextView) itemView.findViewById(R.id.task_title);
                    fav_last_name = (TextView) itemView.findViewById(R.id.task_time);
                    fav_email = (TextView) itemView.findViewById(R.id.task_date_added);
                    fav_rating = (TextView) itemView.findViewById(R.id.user);
                    fav_rank = (TextView) itemView.findViewById(R.id.no_users);
                    fav_phone_number= (TextView) itemView.findViewById(R.id.task_reward_info);
                    fav_profile_pic= (ImageView) itemView.findViewById(R.id.task_image);
                    break;
            }


        }

    }
}
