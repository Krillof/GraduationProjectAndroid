package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HabitsAPI {

    @POST("/set_habit_state")
    Call<String> setHabitState(
            @Query("habit_id") int habitId,
            @Query("habit_state") int habitState,
            @Query("token") String token
    );

    @POST("/create_habit")
    Call<String> createHabit(
            @Body JsonObject habit,
            @Query("token") String token
    );

    @POST("/edit_habit")
    Call<String> editHabit(
            @Body JsonObject habit,
            @Query("token") String token
    );

    @GET("/get_habits")
    Call<String> getHabits(
            @Query("login_from") String loginFrom,
            @Query("login_to") String loginTo,
            @Query("token") String token
    );

}
