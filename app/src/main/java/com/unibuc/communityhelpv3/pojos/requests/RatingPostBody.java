package com.unibuc.communityhelpv3.pojos.requests;

import android.media.Rating;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Tudor on 23.05.2016.
 */
public class RatingPostBody {

    @SerializedName("facebook_token")
    String facebookToken;

    @SerializedName("task_id")
    int taskId;

    @SerializedName("ratings")
    ArrayList<Ratings> ratings;

    public RatingPostBody() {
        ratings = new ArrayList<>();
    }

    public RatingPostBody(String facebookToken, int taskId, ArrayList<Ratings> ratings) {
        this.facebookToken = facebookToken;
        this.taskId = taskId;
        this.ratings = ratings;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public ArrayList<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Ratings> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Ratings rating) {
        ratings.add(rating);
    }

    public void removeRating(int index) {
        if(ratings.size() > index) {
            ratings.remove(index);
        }
    }

    public class Ratings {

        @SerializedName("user_id")
        String userId;

        @SerializedName("rating")
        Float rating;

        public Ratings() {
        }

        public Ratings(String userId, Float rating) {
            this.userId = userId;
            this.rating = rating;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Float getRating() {
            return rating;
        }

        public void setRating(Float rating) {
            this.rating = rating;
        }
    }
}
