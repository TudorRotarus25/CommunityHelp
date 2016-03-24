package com.unibuc.communityhelpv3.rest;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Tudor on 04.03.2016.
 */
public interface RestAPI {

    @GET("api")
    Call<String> TEST_BODY_CALL();
}
