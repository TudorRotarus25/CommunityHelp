package com.unibuc.communityhelpv3.rest;

import com.unibuc.communityhelpv3.pojos.CategoriesGetBody;
import com.unibuc.communityhelpv3.pojos.LoginPostBody;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.UserGetBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Tudor on 04.03.2016.
 */
public interface RestAPI {

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginPostBody> LOGIN_POST_BODY_CALL(@Field("first_name") String firstName, @Field("last_name") String lastName, @Field("facebook_token") String facebookToken, @Field("profile_pic") String profilePicUri);

    @GET("users/profile/{user_id}")
    Call<UserGetBody> USER_GET_BODY_CALL(@Path("user_id") String id);

    @FormUrlEncoded
    @POST("users/profile")
    Call<Void> USER_POST_UPDATE_CALL(@Field("facebook_token") String facebookToken, @Field("first_name") String firstName, @Field("last_name") String lastName, @Field("profile_pic") String profilePic);

    @GET("labels/categories")
    Call<CategoriesGetBody> CATEGORIES_GET_BODY_CALL();

    @FormUrlEncoded
    @POST("tasks/create")
    Call<Void> TASK_POST_CREATE_CALL(@Field("facebook_token") String facebookToken, @Field("title") String title, @Field("description") String description, @Field("category_id") int categoryId, @Field("resource_cost") int resourceCost, @Field("time_cost") int timeCost);

    @FormUrlEncoded
    @POST("tasks/my_tasks")
    Call<TasksGetBody> MY_TASKS_GET_BODY_CALL(@Field("facebook_token") String facebookToken);
}
