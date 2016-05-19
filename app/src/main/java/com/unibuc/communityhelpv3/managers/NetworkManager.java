package com.unibuc.communityhelpv3.managers;

import android.util.Log;

import com.unibuc.communityhelpv3.pojos.CategoriesGetBody;
import com.unibuc.communityhelpv3.pojos.LoginPostBody;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;
import com.unibuc.communityhelpv3.pojos.TasksGetParticipantsBody;
import com.unibuc.communityhelpv3.pojos.UserGetBody;
import com.unibuc.communityhelpv3.pojos.interfaces.AddLocationListener;
import com.unibuc.communityhelpv3.pojos.interfaces.CategoriesListener;
import com.unibuc.communityhelpv3.pojos.interfaces.CreateTaskListener;
import com.unibuc.communityhelpv3.pojos.interfaces.LoginListener;
import com.unibuc.communityhelpv3.pojos.interfaces.TasksListener;
import com.unibuc.communityhelpv3.pojos.interfaces.ProfileListener;
import com.unibuc.communityhelpv3.pojos.interfaces.TasksParticipantsListener;
import com.unibuc.communityhelpv3.pojos.interfaces.UpdateProfileListener;
import com.unibuc.communityhelpv3.rest.RestAPI;
import com.unibuc.communityhelpv3.rest.RestClient;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Tudor on 31.03.2016.
 */
public class NetworkManager {

    private final String TAG = getClass().getSimpleName();

    private static NetworkManager instance = null;
    private RestAPI restAPI;

    private NetworkManager() {
    }

    public static NetworkManager getInstance() {
        if(instance == null) {
            instance = new NetworkManager();
            instance.restAPI = (new RestClient()).getApiService();
        }

        return instance;
    }

    public void login(String firstName, String lastName, String facebookToken, String gcmToken, String profilePicUri, final LoginListener callback) {
        Call<LoginPostBody> call = restAPI.LOGIN_POST_BODY_CALL(firstName, lastName, facebookToken, gcmToken, profilePicUri);
        call.enqueue(new Callback<LoginPostBody>() {
            @Override
            public void onResponse(Response<LoginPostBody> response, Retrofit retrofit) {
                if(response != null && response.body() != null && response.code() == 200) {
                    Log.i(TAG, "Login successful");
                    callback.onLoginSuccess(response.body());
                } else {
                    Log.e(TAG, "Login failed");
                    callback.onLoginFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Login failed: " + t.getMessage());
                callback.onLoginFailed();
            }
        });
    }

    public void getProfile(String userId, final ProfileListener callback) {
        Call<UserGetBody> call = restAPI.USER_GET_BODY_CALL(userId);
        call.enqueue(new Callback<UserGetBody>() {
            @Override
            public void onResponse(Response<UserGetBody> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.code() == 200) {
                    callback.onProfileSuccess(response.body().getProfile());
                } else {
                    Log.e(TAG, response.code() + ": getProfile call failed");
                    callback.onProfileFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "getProfile failed: " + t.getMessage());
                callback.onProfileFailed();
            }
        });
    }

    public void updteProfile(String facebookToken, String firstName, String lastName, String profilePicUri, final UpdateProfileListener callback) {
        Call<Void> call = restAPI.USER_POST_UPDATE_CALL(facebookToken, firstName, lastName, profilePicUri);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if(response != null && response.code() == 200) {
                    callback.onUpdateProfileSuccess();
                } else {
                    Log.e(TAG, response.code() + ": updateProfile failed");
                    callback.onUpdateProfileFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "updateProfile failed: " + t.getMessage());
                callback.onUpdateProfileFailed();
            }
        });
    }

    public void getCategories(final CategoriesListener callback) {
        Call<CategoriesGetBody> call = restAPI.CATEGORIES_GET_BODY_CALL();
        call.enqueue(new Callback<CategoriesGetBody>() {
            @Override
            public void onResponse(Response<CategoriesGetBody> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.code() == 200) {
                    callback.onCategoryGetSuccess(response.body());
                } else {
                    Log.e(TAG, "Get category failed");
                    callback.onCategoryGetFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Get category failed - " + t.getMessage());
                callback.onCategoryGetFailed();
            }
        });
    }

    public void createTask(String facebookToken, String title, String description, int categoryId, int resourceCost, int timeCost, final CreateTaskListener callback) {
        Call<Void> call = restAPI.TASK_POST_CREATE_CALL(facebookToken, title, description, categoryId, resourceCost, timeCost);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if(response != null && response.code() == 200) {
                    callback.onCreateTaskSuccess();
                } else {
                    Log.e(TAG, response.code() + ": createTask Failed");
                    callback.onCreateTaskFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "createTask Failed: " + t.getMessage());
                callback.onCreateTaskFailed();
            }
        });
    }

    public void getMyTasks(String facebookToken, final TasksListener callback) {
        Call<TasksGetBody> call = restAPI.MY_TASKS_GET_BODY_CALL(facebookToken);
        call.enqueue(new Callback<TasksGetBody>() {
            @Override
            public void onResponse(Response<TasksGetBody> response, Retrofit retrofit) {
                if(response != null && response.body() != null && response.code() == 200) {
                    callback.onGetMyTasksSuccess(response.body());
                } else {
                    Log.e(TAG, response.code() + ": getMyTasks failed");
                    callback.onGetMyTasksFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "getMyTasks failed: " + t.getMessage());
                callback.onGetMyTasksFailed();
            }
        });
    }

    public void getOtherPeopleTasks(String facebookToken, final TasksListener callback) {
        Call<TasksGetBody> call = restAPI.OTHER_PEOPLE_TASKS_GET_BODY_CALL(facebookToken);
        call.enqueue(new Callback<TasksGetBody>() {
            @Override
            public void onResponse(Response<TasksGetBody> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.code() == 200) {
                    callback.onGetMyTasksSuccess(response.body());
                } else {
                    Log.e(TAG, "getOtherPeopleTasks failed: " + response.code() + " - " + response.message());
                    callback.onGetMyTasksFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "getOtherPeopleTasks failed: " + t.getMessage());
                callback.onGetMyTasksFailed();
            }
        });
    }

    public void getTaskPendingUsers(String facebookToken, int taskId, final TasksParticipantsListener callback) {
        Call<TasksGetParticipantsBody> call = restAPI.TASKS_GET_PARTICIPANTS_PENDING_BODY_CALL(facebookToken, taskId);
        call.enqueue(new Callback<TasksGetParticipantsBody>() {
            @Override
            public void onResponse(Response<TasksGetParticipantsBody> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.code() == 200) {
                    callback.onGetTasksParticipantsSuccess(response.body());
                } else {
                    Log.e(TAG, "getTaskPendingUsers failed: " + response.code() + " - " + response.message());
                    callback.onGetTasksParticipantsFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "getTaskPendingUsers failed: " + t.getMessage());
                callback.onGetTasksParticipantsFailed();
            }
        });
    }

    public void getTaskConfirmedUsers(String facebookToken, int taskId, final TasksParticipantsListener callback) {
        Call<TasksGetParticipantsBody> call = restAPI.TASKS_GET_PARTICIPANTS_CONFIRMED_BODY_CALL(facebookToken, taskId);
        call.enqueue(new Callback<TasksGetParticipantsBody>() {
            @Override
            public void onResponse(Response<TasksGetParticipantsBody> response, Retrofit retrofit) {
                if (response != null && response.body() != null && response.code() == 200) {
                    callback.onGetTasksParticipantsSuccess(response.body());
                } else {
                    Log.e(TAG, "getTaskPendingUsers failed: " + response.code() + " - " + response.message());
                    callback.onGetTasksParticipantsFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "getTaskPendingUsers failed: " + t.getMessage());
                callback.onGetTasksParticipantsFailed();
            }
        });
    }

    //// TODO: 19.05.2016 Specify location type in call
    public void addLocation(String facebookToken, String name, String address, Double lat, Double lng, final int type, final AddLocationListener callback) {
        Call<Void> call = restAPI.LOCATIONS_ADD_BODY_CALL(facebookToken, name, address, lat, lng);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if(response != null && response.code() == 200) {
                    callback.onAddLocationSuccess(type);
                } else {
                    Log.e(TAG, "addLocation failed: " + response.code());
                    callback.onAddLocationFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "addLocation failed: " + t.getMessage());
                callback.onAddLocationFailed();
            }
        });
    }
}
