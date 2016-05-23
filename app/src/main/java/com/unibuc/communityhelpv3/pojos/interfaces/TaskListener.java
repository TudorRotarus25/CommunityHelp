package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.TaskDetails;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.requests.TaskDetailsGetBody;

/**
 * Created by Luci on 23/05/2016.
 */
public interface TaskListener {
    void onGetTaskSucces(TaskDetailsGetBody task);
    void onGetTaskFailed();
}
