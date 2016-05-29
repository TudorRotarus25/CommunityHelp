package com.unibuc.communityhelpv3.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.dialogs.ConfirmedUsersDialog;
import com.unibuc.communityhelpv3.dialogs.PendingUsersDialog;
import com.unibuc.communityhelpv3.adapters.interfaces.OnMyTaskClickedInterface;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class MyTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnMyTaskClickedInterface onMyTaskClickedInterface;

    private final String TAG = getClass().getSimpleName();

    Context context;
    String fragment_type;
    ArrayList<TasksGetBody.Task> tasksArrayList;

    public MyTasksAdapter(Context mContext, ArrayList<TasksGetBody.Task> tasksArrayList, String fragment_type,
                          OnMyTaskClickedInterface onMyTaskClickedInterface) {
        this.context = mContext;
        this.tasksArrayList = tasksArrayList;
        this.fragment_type = fragment_type;
        this.onMyTaskClickedInterface = onMyTaskClickedInterface;

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
        final TasksGetBody.Task task = tasksArrayList.get(position);
        ((MyTaskViewHolder) holder).titleTextView.setText(task.getTitle());
        ((MyTaskViewHolder) holder).descriptionTextView.setText(task.getDescription());
        ((MyTaskViewHolder) holder).resourceCostTextView.setText("" + task.getResource_cost());


        ((MyTaskViewHolder) holder).pendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingUsersDialog pendingDialog = PendingUsersDialog.newInstance(Integer.parseInt(task.getId()));
                pendingDialog.show(((Activity) context).getFragmentManager(), TAG);
            }
        });
        ((MyTaskViewHolder) holder).confirmedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmedUsersDialog confirmedDialog = ConfirmedUsersDialog.newInstance(Integer.parseInt(task.getId()));
                confirmedDialog.show(((Activity) context).getFragmentManager(), TAG);
            }
        });
        //Log.d("DEBUG ", "TASK ADAPTER BIND VIEW HOLDER");

        ((MyTaskViewHolder) holder).mainContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyTaskClickedInterface.onMyTaskClicked(task);
            }
        });
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
        Button pendingButton;
        Button confirmedButton;
        LinearLayout mainContent;

        public MyTaskViewHolder(View view) {
            super(view);
            mainContent = (LinearLayout) itemView.findViewById(R.id.layout_my_task_mainContent);
            titleTextView = (TextView) itemView.findViewById(R.id.layout_my_task_title_textView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.layout_my_task_description_textView);
            resourceCostTextView = (TextView) itemView.findViewById(R.id.layout_my_task_stars_textView);
            iconImageView = (ImageView) itemView.findViewById(R.id.layout_my_task_icon_imageView);
            pendingButton = (Button) itemView.findViewById(R.id.layout_my_task_pending_button);
            confirmedButton = (Button) itemView.findViewById(R.id.layout_my_task_confirmed_button);
        }

    }

    public ArrayList<TasksGetBody.Task> getTasksArrayList() {
        return tasksArrayList;
    }

    public void setTasksArrayList(ArrayList<TasksGetBody.Task> tasksArrayList) {
        this.tasksArrayList = tasksArrayList;
    }
}
