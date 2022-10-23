package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TasksAPI {
    @POST("/set_task_state")
    Call<String> setTaskState(
            @Query("task_id") int taskId,
            @Query("task_state") int taskState,
            @Query("token") String token
    );

    @POST("/create_task")
    Call<String> createTask(
            @Body JsonObject task,
            @Query("token") String token
    );

    @POST("/edit_task")
    Call<String> editTask(
            @Body JsonObject task,
            @Query("token") String token
    );

    @GET("/get_tasks")
    Call<String> getTasks(
            @Query("login_from") String loginFrom,
            @Query("login_to") String loginTo,
            @Query("token") String token
    );
}
