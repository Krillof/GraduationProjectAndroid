package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.endpoints.ServerAnswer
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Query

interface UserStudentsAPI {
    @GET("/get_user_students")
    fun getUserStudents(
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @GET("/get_study_requests")
    fun getStudyRequests(
        @Query("login_starts_with") loginStartsWith: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/abandon_study_with_student")
    fun abandonStudyWithStudent(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/abandon_study_request")
    fun abandonStudyRequest(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @POST("/accept_study_request")
    fun acceptStudyRequest(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>
}