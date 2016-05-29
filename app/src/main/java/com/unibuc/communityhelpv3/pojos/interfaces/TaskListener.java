package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.TaskDetailsGetBody;

/**
 * Created by Luci on 23/05/2016.
 */
public interface TaskListener {
    void onGetTaskSucces(TaskDetailsGetBody task);
    void onGetTaskFailed();
}
