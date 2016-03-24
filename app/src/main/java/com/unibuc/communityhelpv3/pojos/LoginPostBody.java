package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Tudor on 24.03.2016.
 */
public class LoginPostBody {
    int user_id;
    int status;
    String content;

    public LoginPostBody(int user_id, int status, String content) {
        this.user_id = user_id;
        this.status = status;
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
