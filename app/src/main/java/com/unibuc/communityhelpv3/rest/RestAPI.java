package com.unibuc.communityhelpv3.rest;

import com.unibuc.communityhelpv3.pojos.CategoriesGetBody;
import com.unibuc.communityhelpv3.pojos.LoginPostBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Tudor on 04.03.2016.
 */
public interface RestAPI {

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginPostBody> LOGIN_POST_BODY_CALL(@Field("first_name") String firstName, @Field("last_name") String lastName, @Field("facebook_token") String facebookToken, @Field("profile_pic") String profilePicUri);

    @GET("labels/categories")
    Call<CategoriesGetBody> CATEORIES_GET_BODY_CALL();
 }
