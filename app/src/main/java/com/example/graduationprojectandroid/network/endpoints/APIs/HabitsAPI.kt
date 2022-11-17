package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.example.graduationprojectandroid.data.Items.Habit;
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HabitsAPI {

    @POST("/set_habit_state")
    Call<SimpleServerAnswer> setHabitState(
            @Query("habit_id") int habitId,
            @Query("habit_state") int habitState,
            @Query("token") String token
    );

    @POST("/create_habit")
    Call<SimpleServerAnswer> createHabit(
            @Body Habit habit,
            @Query("token") String token
    );

    @POST("/edit_habit")
    Call<SimpleServerAnswer> editHabit(
            @Body Habit habit,
            @Query("token") String token
    );

    @GET("/get_habits")
    Call<SimpleServerAnswer> getHabits(
            @Query("login_from") String loginFrom,
            @Query("login_to") String loginTo,
            @Query("token") String token
    );

}
