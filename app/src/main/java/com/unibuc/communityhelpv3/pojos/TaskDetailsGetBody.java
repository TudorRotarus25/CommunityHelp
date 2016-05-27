package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Serban Theodor on 19-May-16.
 */
public class TaskDetailsGetBody {
    TasksGetBody.Task task;

    public TaskDetailsGetBody(TasksGetBody.Task task) {
        this.task = task;
    }

    public TasksGetBody.Task getTask() {
        return task;
    }

    public void setTask(TasksGetBody.Task task) {
        this.task = task;
    }
}
