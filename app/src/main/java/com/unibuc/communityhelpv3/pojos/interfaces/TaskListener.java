package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.TaskDetails;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;

/**
 * Created by Luci on 23/05/2016.
 */
public interface TaskListener {
    void onGetTaskSucces(TaskDetails task);
    void onGetTaskFailed();
}
