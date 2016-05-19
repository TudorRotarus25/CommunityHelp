package com.unibuc.communityhelpv3.pojos;

import java.util.ArrayList;

/**
 * Created by Tudor on 11.05.2016.
 */
public class TasksGetParticipantsBody {
    ArrayList<UserGetBody.User> participants;

    public TasksGetParticipantsBody() {
    }

    public TasksGetParticipantsBody(ArrayList<UserGetBody.User> participants) {
        this.participants = participants;
    }

    public ArrayList<UserGetBody.User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<UserGetBody.User> participants) {
        this.participants = participants;
    }
}
