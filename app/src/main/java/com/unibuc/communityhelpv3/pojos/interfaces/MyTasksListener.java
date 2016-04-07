package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.TasksGetBody;

/**
 * Created by Tudor on 07.04.2016.
 */
public interface MyTasksListener {
    void onGetMyTasksSuccess(TasksGetBody response);
    void onGetMyTasksFailed();
}
