package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.data.Items.Task
import retrofit2.http.GET
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import retrofit2.http.POST
import com.example.graduationprojectandroid.network.endpoints.UserValidationAnswer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Query

interface TasksAPI {
    @POST("/set_task_state")
    fun setTaskState(
        @Query("task_id") taskId: Int,
        @Query("task_state") taskState: Int,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/create_task")
    fun createTask(
        @Body task: Task?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/edit_task")
    fun editTask(
        @Body task: Task?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @GET("/get_tasks")
    fun getTasks(
        @Query("login_from") loginFrom: String?,
        @Query("login_to") loginTo: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>
}