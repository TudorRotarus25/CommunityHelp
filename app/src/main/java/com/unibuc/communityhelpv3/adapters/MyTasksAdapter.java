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
public class MyTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String fragment_type;
    ArrayList<TasksGetBody.Task> tasksArrayList;

    public MyTasksAdapter(Context mContext, ArrayList<TasksGetBody.Task> tasksArrayList, String fragment_type) {
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
        TasksGetBody.Task task = tasksArrayList.get(position);
        ((MyTaskViewHolder) holder).titleTextView.setText(task.getTitle());
        ((MyTaskViewHolder) holder).descriptionTextView.setText(task.getDescription());
        ((MyTaskViewHolder) holder).resourceCostTextView.setText("" + task.getResource_cost());
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
        TextView titleTextView;
        TextView descriptionTextView;
        TextView resourceCostTextView;
        ImageView iconImageView;

        public MyTaskViewHolder(View view) {
            super(view);
            titleTextView = (TextView) itemView.findViewById(R.id.layout_my_task_title_textView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.layout_my_task_description_textView);
            resourceCostTextView = (TextView) itemView.findViewById(R.id.layout_my_task_stars_textView);
            iconImageView = (ImageView) itemView.findViewById(R.id.layout_my_task_icon_imageView);
        }

    }

    public ArrayList<TasksGetBody.Task> getTasksArrayList() {
        return tasksArrayList;
    }

    public void setTasksArrayList(ArrayList<TasksGetBody.Task> tasksArrayList) {
        this.tasksArrayList = tasksArrayList;
    }
}
