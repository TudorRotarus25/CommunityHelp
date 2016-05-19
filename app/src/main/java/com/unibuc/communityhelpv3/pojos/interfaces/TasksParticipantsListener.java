package com.unibuc.communityhelpv3.pojos.interfaces;

import com.unibuc.communityhelpv3.pojos.TasksGetParticipantsBody;

/**
 * Created by Tudor on 11.05.2016.
 */
public interface TasksParticipantsListener {
    void onGetTasksParticipantsSuccess(TasksGetParticipantsBody response);
    void onGetTasksParticipantsFailed();
}
