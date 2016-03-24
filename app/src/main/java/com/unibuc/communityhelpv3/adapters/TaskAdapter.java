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
    int viewTaskType;
    ArrayList<TaskGetBody> tasksArrayList;

    public TaskAdapter(Context mContext, ArrayList<TaskGetBody> tasksArrayList) {
        this.context = mContext;
        this.tasksArrayList = tasksArrayList;

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_my_task, parent, false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TaskGetBody task = tasksArrayList.get(position);
        ((ViewHolder) holder).task_tittle.setText(task.getContent());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView task_tittle, task_time, task_date_added, task_user, no_users, task_reward_info;
        ImageView task_image_url;

        public ViewHolder(View view) {
            super(view);
            task_tittle = (TextView) itemView.findViewById(R.id.task_tittle);
            task_time = (TextView) itemView.findViewById(R.id.task_time);
            task_date_added = (TextView) itemView.findViewById(R.id.task_date_added);
            task_user = (TextView) itemView.findViewById(R.id.user);
            no_users = (TextView) itemView.findViewById(R.id.no_users);
            task_reward_info = (TextView) itemView.findViewById(R.id.task_reward_info);
            task_image_url = (ImageView) itemView.findViewById(R.id.task_image);

        }
    }
}
