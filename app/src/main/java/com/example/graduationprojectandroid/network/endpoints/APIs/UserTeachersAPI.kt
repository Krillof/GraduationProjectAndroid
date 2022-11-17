package com.example.graduationprojectandroid.network.endpoints.APIs

import retrofit2.http.GET
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import retrofit2.http.POST
import com.example.graduationprojectandroid.network.endpoints.UserValidationAnswer
import retrofit2.Call
import retrofit2.http.Query

interface UserTeachersAPI {
    @GET("/get_user_teachers")
    fun getUserTeachers(
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @GET("/get_teachers_to_find")
    fun getTeachersToFind(
        @Query("login_starts_with") loginStartsWith: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/abandon_study_with_teacher")
    fun abandonStudyWithTeacher(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/make_study_request")
    fun makeStudyRequest(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>
}