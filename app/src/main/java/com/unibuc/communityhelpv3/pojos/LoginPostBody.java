package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Tudor on 24.03.2016.
 */
public class LoginPostBody {
    String user_id;
    String status;

    public LoginPostBody(String user_id, String status) {
        this.user_id = user_id;
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
