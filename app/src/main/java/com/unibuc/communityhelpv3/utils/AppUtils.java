package com.unibuc.communityhelpv3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.unibuc.communityhelpv3.pojos.TasksGetBody;

/**
 * Created by Luci on 11/05/2016.
 */
public class AppUtils {
    private static final String PREF_NAME = "SHARED_PREFS";

    public static TasksGetBody.Task getCurrentTask(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("currentTask", "");
        if (json == "") return null;
        else {
            TasksGetBody.Task task = gson.fromJson(json, TasksGetBody.Task.class);
            return task;
        }
    }

    public static void storeCurrentTask(TasksGetBody.Task task, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(task);
        editor.putString("currentTask", json);
        editor.commit();
    }
}
