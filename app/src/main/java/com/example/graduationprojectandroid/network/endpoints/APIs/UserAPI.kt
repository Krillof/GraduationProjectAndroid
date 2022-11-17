package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.UserData
import retrofit2.http.GET
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import retrofit2.http.POST
import com.example.graduationprojectandroid.network.endpoints.UserValidationAnswer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Query
import java.util.ArrayList

interface UserAPI {
    @POST("/register")
    fun register(
        @Query("login") login: String?, @Query("password") password: String?
    ): Call<UserValidationAnswer>

    @POST("/enter")
    fun enter(
        @Query("login") login: String?, @Query("password") password: String?
    ): Call<UserValidationAnswer>

    @GET("/check_token")
    fun checkToken(
        @Query("check_token") token: String?
    ): Call<SimpleServerAnswer>

    @GET("/get_user_data")
    fun getUserData(
        @Query("token") token: String?
    ): Call<UserData>

    // for avatar
    @POST("/changed_avatar")
    fun changedAvatar(
        @Body choosenParts: ArrayList<Int?>?,
        @Query("avatar_name") avatarName: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @GET("/check_avatar_name")
    fun checkAvatarName(
        @Query("avatar_name") avatarName: String?
    ): Call<SimpleServerAnswer>

    @GET("/get_amount_of_one_avatar_part_type")
    fun getAmountOfOneAvatarPartType(
        @Query("avatar_part_type") partNumber: String?
    ): Call<SimpleServerAnswer>
}