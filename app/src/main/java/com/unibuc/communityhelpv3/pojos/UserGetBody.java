package com.unibuc.communityhelpv3.pojos;

/**
 * Created by Serban Theodor on 24-Mar-16.
 */
public class UserGetBody {
    String user_id, first_name, last_name, profile_pic, rating_value, rank_value, resource_value, phone_number, email, address, city;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setResource_value(String resource_value) {
        this.resource_value = resource_value;
    }

    public String getResource_value() {
        return resource_value;
    }

    public void setAddress(String address) {
        this.address= address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
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

    public String getRating_value() {
        return rating_value;
    }

    public void setRating_value(String rating_value) {
        this.rating_value = rating_value;
    }

    public String getRank_value() {
        return rank_value;
    }

    public void setRank_value(String rank_value) {
        this.rank_value = rank_value;
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

    public UserGetBody(String user_id, String first_name, String last_name, String profile_pic, String rating_value, String rank_value,
                       String phone_number, String email, String address, String city) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_pic = profile_pic;
        this.rating_value = rating_value;
        this.rank_value = rank_value;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.city = city;
    }
}
