package com.example.graduationprojectandroid.network.endpoints.APIs

import retrofit2.http.GET
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import retrofit2.http.POST
import com.example.graduationprojectandroid.network.endpoints.UserValidationAnswer
import retrofit2.Call
import retrofit2.http.Query

interface UserStudentsAPI {
    @GET("/get_user_students")
    fun getUserStudents(
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @GET("/get_study_requests")
    fun getStudyRequests(
        @Query("login_starts_with") loginStartsWith: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/abandon_study_with_student")
    fun abandonStudyWithStudent(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/abandon_study_request")
    fun abandonStudyRequest(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @POST("/accept_study_request")
    fun acceptStudyRequest(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>
}