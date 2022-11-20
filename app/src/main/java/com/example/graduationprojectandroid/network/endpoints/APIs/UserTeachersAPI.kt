package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.endpoints.ServerAnswer
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Query

interface UserTeachersAPI {
    @GET("/get_user_teachers")
    fun getUserTeachers(
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @GET("/get_teachers_to_find")
    fun getTeachersToFind(
        @Query("login_starts_with") loginStartsWith: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/abandon_study_with_teacher")
    fun abandonStudyWithTeacher(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/make_study_request")
    fun makeStudyRequest(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>
}