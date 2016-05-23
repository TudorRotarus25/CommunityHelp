package com.unibuc.communityhelpv3.pojos.requests;

import com.unibuc.communityhelpv3.pojos.TaskDetails;

/**
 * Created by Luci on 23/05/2016.
 */
public class TaskDetailsGetBody {
    TaskDetails task;

    public TaskDetails getTask() {
        return task;
    }

    public void setTask(TaskDetails task) {
        this.task = task;
    }
}
