package com.unibuc.communityhelpv3.pojos;

import java.io.Serializable;

/**
 * Created by Serban Theodor on 24-Mar-16.
 */
public class UserGetBody {

    User profile;

    public UserGetBody()
    {
        profile = new User();
    }

    public UserGetBody(User profile) {
        this.profile = profile;
    }

    public User getProfile() {
        return profile;
    }

    public void setProfile(User profile) {
        this.profile = profile;
    }

    public class User implements Serializable{

        String id;
        String first_name;
        String last_name;
        String email;
        String phone_number;
        String profile_pic;
        String rating;
        String rank;
        int nr_tasks;

        public User() {
        }

        public User(String id, String first_name, String last_name, String email, String phone_number, String profile_pic, String rating, String rank, int nr_tasks) {
            this.id = id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.phone_number = phone_number;
            this.profile_pic = profile_pic;
            this.rating = rating;
            this.rank = rank;
            this.nr_tasks = nr_tasks;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getNr_tasks() {
            return nr_tasks;
        }

        public void setNr_tasks(int nr_tasks) {
            this.nr_tasks = nr_tasks;
        }
    }
}
