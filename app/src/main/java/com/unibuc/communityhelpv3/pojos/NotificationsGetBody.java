package com.unibuc.communityhelpv3.pojos;

import java.util.ArrayList;

/**
 * Created by Serban Theodor on 13-May-16.
 */
public class NotificationsGetBody {

    ArrayList<Notification> notifications;

    public NotificationsGetBody() {

    }

    public NotificationsGetBody(ArrayList<Notification> tasks) {
        this.notifications = tasks;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> tasks) {
        this.notifications = tasks;
    }

    public class Notification{
        String title;
        String text;
        String notification_type;

        String task_id;
        String user_id;
        String date_received;

        public Notification(String title, String text, String notification_type, String task_id, String user_id, String date_received) {
            this.title = title;
            this.text = text;
            this.notification_type = notification_type;
            this.task_id = task_id;
            this.user_id = user_id;
            this.date_received = date_received;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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

        public String getDate_received() {
            return date_received;
        }

        public void setDate_received(String date_received) {
            this.date_received = date_received;
        }
    }
}
