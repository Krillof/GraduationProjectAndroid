package com.example.graduationprojectandroid.network.endpoints.APIs

import retrofit2.http.POST
import com.example.graduationprojectandroid.data.Items.Habit
import com.example.graduationprojectandroid.network.endpoints.ServerAnswer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface HabitsAPI {
    @POST("/set_habit_state")
    fun setHabitState(
        @Query("habit_id") habitId: Int,
        @Query("habit_state") habitState: Int,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/create_habit")
    fun createHabit(
        @Body habit: Habit?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/edit_habit")
    fun editHabit(
        @Body habit: Habit?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @GET("/get_habits")
    fun getHabits(
        @Query("login_from") loginFrom: String?,
        @Query("login_to") loginTo: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>
}