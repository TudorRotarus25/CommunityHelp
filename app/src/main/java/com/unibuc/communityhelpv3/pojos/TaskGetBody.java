package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Serban Theodor on 17-Mar-16.
 */
public class TaskGetBody {
    String task_title, task_description, task_user_owner, task_no_users, task_time,
            task_date_added, task_reward_info, task_category, task_image_url;

    public TaskGetBody(String task_title, String task_description, String task_user_owner, String task_no_users,
                       String task_time, String task_date_added, String task_reward_info, String task_category, String task_image_url) {
        this.task_title = task_title;
        this.task_description = task_description;
        this.task_user_owner = task_user_owner;
        this.task_no_users = task_no_users;
        this.task_time = task_time;
        this.task_date_added = task_date_added;
        this.task_reward_info = task_reward_info;
        this.task_category = task_category;
        this.task_image_url = task_image_url;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_desription() {
        return task_description;
    }

    public void setTask_desription(String task_desription) {
        this.task_description = task_desription;
    }

    public String getTask_user_owner() {
        return task_user_owner;
    }

    public void setTask_user_owner(String task_user_owner) {
        this.task_user_owner = task_user_owner;
    }

    public String getTask_no_users() {
        return task_no_users;
    }

    public void setTask_no_users(String task_no_users) {
        this.task_no_users = task_no_users;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }

    public String getTask_date_added() {
        return task_date_added;
    }

    public void setTask_date_added(String task_date_added) {
        this.task_date_added = task_date_added;
    }

    public String getTask_reward_info() {
        return task_reward_info;
    }

    public void setTask_reward_info(String task_reward_info) {
        this.task_reward_info = task_reward_info;
    }

    public String getTask_image_url() {
        return task_image_url;
    }

    public void setTask_image_url(String task_image_url) {
        this.task_image_url = task_image_url;
    }
}
