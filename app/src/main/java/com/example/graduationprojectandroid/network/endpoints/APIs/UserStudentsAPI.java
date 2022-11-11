package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserStudentsAPI {
    @GET("/get_user_students")
    Call<SimpleServerAnswer> getUserStudents(
            @Query("token") String token
    );

    @GET("/get_study_requests")
    Call<SimpleServerAnswer> getStudyRequests(
            @Query("login_starts_with") String loginStartsWith,
            @Query("token") String token
    );

    @POST("/abandon_study_with_student")
    Call<SimpleServerAnswer> abandonStudyWithStudent(
            @Query("login") String login,
            @Query("token") String token
    );

    @POST("/abandon_study_request")
    Call<SimpleServerAnswer> abandonStudyRequest(
            @Query("login") String login,
            @Query("token") String token
    );

    @POST("/accept_study_request")
    Call<SimpleServerAnswer> acceptStudyRequest(
            @Query("login") String login,
            @Query("token") String token
    );
}
