package com.unibuc.communityhelpv3.pojos;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 13-May-16.
 */
public class NotificationsGetBody {

    ArrayList<Notification> notifications;

    public NotificationsGetBody() {

    }

    public NotificationsGetBody(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public class Notification{
        String title;
        String message;
        String notification_type;

        String task_id;
        String user_id;
        String created_at;
        String id;


        String seen;

        public Notification(String title, String text, String notification_type, String task_id, String user_id,
                            String notification_id, String created_at, String first_time) {
            this.title = title;
            this.message = text;
            this.notification_type = notification_type;
            this.task_id = task_id;
            this.user_id = user_id;
            this.created_at = created_at;
            this.id = notification_id;
            this.seen = first_time;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSeen() {
            return seen;
        }

        public void setSeen(String seen) {
            this.seen = seen;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNotification_type() {
            return notification_type;
        }

        public void setNotification_type(String notification_type) {
            this.notification_type = notification_type;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
