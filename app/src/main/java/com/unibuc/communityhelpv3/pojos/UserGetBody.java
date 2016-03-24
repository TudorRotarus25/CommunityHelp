package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Serban Theodor on 24-Mar-16.
 */
public class UserGetBody {
    int user_id;
    String first_name, last_name, profile_pic, rating, rank, phone_number, email;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGetBody(int user_id, String first_name, String last_name, String profile_pic, String rating, String rank, String phone_number, String email) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_pic = profile_pic;
        this.rating = rating;
        this.rank = rank;
        this.phone_number = phone_number;
        this.email = email;
    }
}
