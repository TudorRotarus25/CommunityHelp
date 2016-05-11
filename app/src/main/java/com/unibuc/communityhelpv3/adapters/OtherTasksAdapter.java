package com.unibuc.communityhelpv3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class OtherTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String fragment_type;
    ArrayList<TasksGetBody.Task> tasksArrayList;

    public OtherTasksAdapter(Context mContext, ArrayList<TasksGetBody.Task> tasksArrayList, String fragment_type) {
        this.context = mContext;
        this.tasksArrayList = tasksArrayList;
        this.fragment_type = fragment_type;

    }

    public ArrayList<TasksGetBody.Task> getTasksArrayList() {
        return tasksArrayList;
    }

    public void setTasksArrayList(ArrayList<TasksGetBody.Task> tasksArrayList) {
        this.tasksArrayList = tasksArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_other_tasks, parent, false);
        return new OtherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TasksGetBody.Task task = tasksArrayList.get(position);
        ((OtherViewHolder) holder).other_task_title.setText(task.getTitle());
        ((OtherViewHolder) holder).other_task_time.setText("" + task.getTime_cost());
        ((OtherViewHolder) holder).other_task_date_added.setText(task.getCreated_at());
        ((OtherViewHolder) holder).other_task_user.setText("" + task.getOwner_id());
        ((OtherViewHolder) holder).other_no_users.setText("" + task.getParticipants_number());
        ((OtherViewHolder) holder).other_task_reward_info.setText("" + task.getResource_cost());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tasksArrayList.size();
    }


    public class OtherViewHolder extends RecyclerView.ViewHolder {

        //other tasks
        TextView other_task_title, other_task_time, other_task_date_added, other_task_user,
                other_no_users, other_task_reward_info;
        ImageView other_task_image_url;

        public OtherViewHolder(View view) {
            super(view);
            other_task_title = (TextView) itemView.findViewById(R.id.layout_other_tasks_title_textView);
            other_task_time = (TextView) itemView.findViewById(R.id.layout_other_tasks_duration_textView);
            other_task_date_added = (TextView) itemView.findViewById(R.id.layout_other_tasks_date_textView);
            other_task_user = (TextView) itemView.findViewById(R.id.layout_other_tasks_username_textView);
            other_no_users = (TextView) itemView.findViewById(R.id.layout_other_tasks_participants_textView);
            other_task_reward_info = (TextView) itemView.findViewById(R.id.layout_other_tasks_reward_textView);
            other_task_image_url = (ImageView) itemView.findViewById(R.id.layout_other_tasks_icon);
        }

    }
}
