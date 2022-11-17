package com.example.graduationprojectandroid.network.endpoints.APIs;
import com.example.graduationprojectandroid.network.UserData;
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer;
import com.google.gson.JsonObject;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {

    // for registration
    @POST("/is_login_unique")
    Call<SimpleServerAnswer> isLoginUnique(
            @Query("login") String login
    );

    @POST("/is_password_ok")
    Call<SimpleServerAnswer> isPasswordOK(
            @Query("password") String password
    );

    @POST("/register")
    Call<SimpleServerAnswer> register(
            @Query("login") String login, @Query("password") String password
    );




    // log in
    @POST("/check_login_existence")
    Call<SimpleServerAnswer> isLoginExists(
            @Query("login") String login
    );

    @POST("/enter")
    Call<SimpleServerAnswer> enter(
            @Query("login") String login, @Query("password") String password
    );
    
    @GET("/check_token")
    Call<SimpleServerAnswer> checkToken(
            @Query("check_token") String token
    );







    @GET("/get_user_data")
    Call<UserData> getUserData(
            @Query("token") String token
    );





    // for avatar
    @POST("/changed_avatar")
    Call<SimpleServerAnswer> changedAvatar(
            @Body ArrayList<Integer> choosenParts,
            @Query("avatar_name") String avatarName,
            @Query("token") String token
    );

    @GET("/check_avatar_name")
    Call<SimpleServerAnswer> checkAvatarName(@Query("avatar_name") String avatarName);

    @GET("/get_amount_of_one_avatar_part_type")
    Call<SimpleServerAnswer> getAmountOfOneAvatarPartType(@Query("avatar_part_type") String partNumber);

}
