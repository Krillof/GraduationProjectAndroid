package com.example.graduationprojectandroid.network.endpoints.APIs;
import com.example.graduationprojectandroid.network.UserData;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {

    // for registration
    @POST("/is_login_unique")
    Call<String> isLoginUnique(
            @Query("login") String login
    );

    @POST("/is_password_ok")
    Call<String> isPasswordOK(
            @Query("password") String password
    );

    @POST("/register")
    Call<String> register(
            @Query("login") String login, @Query("password") String password
    );




    // log in
    @POST("/check_login_existence")
    Call<String> isLoginExists(
            @Query("login") String login
    );

    @POST("/enter")
    Call<String> enter(
            @Query("login") String login, @Query("password") String password
    );


    @GET("/check_token")
    Call<Boolean> checkToken(
            @Query("check_token") String token
    );







    @GET("/get_user_data")
    Call<UserData> getUserData(
            @Query("login") String login, @Query("token") String token
    );





    // for avatar
    @POST("/changed_avatar")
    Call<String> changedAvatar(@Body JsonObject jsonObject, @Query("token") String token);

    @GET("/check_avatar_name")
    Call<String> checkAvatarName(@Query("avatar_name") String avatarName);

}
