package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserTeachersAPI {
    @GET("/get_user_teachers")
    Call<SimpleServerAnswer> getUserTeachers(
            @Query("token") String token
    );

    @GET("/get_teachers_to_find")
    Call<SimpleServerAnswer> getTeachersToFind(
            @Query("login_starts_with") String loginStartsWith,
            @Query("token") String token
    );

    @POST("/abandon_study_with_teacher")
    Call<SimpleServerAnswer> abandonStudyWithTeacher(
            @Query("login") String login,
            @Query("token") String token
    );

    @POST("/make_study_request")
    Call<SimpleServerAnswer> makeStudyRequest(
            @Query("login") String login,
            @Query("token") String token
    );
}
