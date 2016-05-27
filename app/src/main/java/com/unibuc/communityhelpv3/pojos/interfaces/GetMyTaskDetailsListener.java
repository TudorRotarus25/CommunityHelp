package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.NotificationsGetBody;
import com.unibuc.communityhelpv3.pojos.TaskDetailsGetBody;

/**
 * Created by Serban Theodor on 19-May-16.
 */
public interface GetMyTaskDetailsListener {
    void onGetMyTaskDetailsSuccess(TaskDetailsGetBody response);
    void onGetMyTaskDetailsFailed();
}
