package com.unibuc.communityhelpv3.pojos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tudor on 07.04.2016.
 */
public class TasksGetBody {

    ArrayList<Task> tasks;

    public TasksGetBody(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public class Task implements Serializable {
        String participant_status;
        String location_name;
        String location_lat;
        String location_lng;
        String name;
        int participants_number;
        String rating;
        String category_title;
        String category_picture;
        String id;
        String title;
        String category_id;
        String resource_cost;
        String description;
        String location_id;
        String time_cost;
        String owner_id;
        String status;
        String created_at;
        String updated_at;

        public String getCategory_title() {
            return category_title;
        }

        public void setCategory_title(String category_title) {
            this.category_title = category_title;
        }

        public String getCategory_picture() {
            return category_picture;
        }

        public void setCategory_picture(String category_picture) {
            this.category_picture = category_picture;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public void setResource_cost(String resource_cost) {
            this.resource_cost = resource_cost;
        }

        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public void setTime_cost(String time_cost) {
            this.time_cost = time_cost;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public Task(String participant_status, String location_name, String location_lat, String location_lng, String name, int participants_number, String rating, String category_title, String category_picture, String id, String title, String category_id, String resource_cost, String description, String location_id, String time_cost, String owner_id, String status, String created_at, String updated_at) {
            this.participant_status = participant_status;
            this.location_name = location_name;
            this.location_lat = location_lat;
            this.location_lng = location_lng;
            this.name = name;
            this.participants_number = participants_number;
            this.rating = rating;
            this.category_title = category_title;
            this.category_picture = category_picture;
            this.id = id;
            this.title = title;
            this.category_id = category_id;
            this.resource_cost = resource_cost;
            this.description = description;
            this.location_id = location_id;
            this.time_cost = time_cost;
            this.owner_id = owner_id;
            this.status = status;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public String getParticipant_status() {
            return participant_status;
        }

        public void setParticipant_status(String participant_status) {
            this.participant_status = participant_status;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getLocation_lat() {
            return location_lat;
        }

        public void setLocation_lat(String location_lat) {
            this.location_lat = location_lat;
        }

        public String getLocation_lng() {
            return location_lng;
        }

        public void setLocation_lng(String location_lng) {
            this.location_lng = location_lng;
        }

        public int getParticipants_number() {
            return participants_number;
        }

        public void setParticipants_number(int participants_number) {
            this.participants_number = participants_number;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategory_id() {
            return category_id;
        }

        public String getResource_cost() {
            return resource_cost;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTime_cost() {
            return time_cost;
        }

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public String getStatus() {
            return status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
