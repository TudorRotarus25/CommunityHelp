package com.unibuc.communityhelpv3.pojos;

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

    public class Task {

        int id;
        String title;
        int category_id;
        int resource_cost;
        String description;
        String location_name;
        String location_lat;
        String location_lng;
        int time_cost;
        String owner_id;
        String name;
        int status;
        int participants_number;
        String created_at;
        String updated_at;
        String rating;

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

        public Task(int id, String title, int category_id, int resource_cost, String description, String location_name,
                    String rating, String location_lat, String location_lng, int time_cost, String owner_id, int status,
                    int participants_number, String created_at, String updated_at, String name) {
            this.id = id;
            this.name = name;
            this.rating = rating;
            this.title = title;
            this.category_id = category_id;
            this.resource_cost = resource_cost;
            this.description = description;
            this.location_name = location_name;
            this.location_lat = location_lat;
            this.location_lng = location_lng;
            this.time_cost = time_cost;
            this.owner_id = owner_id;
            this.status = status;
            this.participants_number = participants_number;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getResource_cost() {
            return resource_cost;
        }

        public void setResource_cost(int resource_cost) {
            this.resource_cost = resource_cost;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getTime_cost() {
            return time_cost;
        }

        public void setTime_cost(int time_cost) {
            this.time_cost = time_cost;
        }

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getParticipants_number() {
            return participants_number;
        }

        public void setParticipants_number(int participants_number) {
            this.participants_number = participants_number;
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
