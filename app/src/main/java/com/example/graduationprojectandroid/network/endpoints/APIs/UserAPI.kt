package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.UserData
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import com.example.graduationprojectandroid.network.endpoints.UserValidationAnswer
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList

interface UserAPI {
    @GET("mobileappusers/tryregister")
    fun tryRegister(
        @Query("login") login: String?,
        @Query("password") password: String?
    ): Call<UserValidationAnswer>

    @GET("mobileappusers/tryenter")
    fun tryEnter(
        @Query("login") login: String?,
        @Query("password") password: String?
    ): Call<UserValidationAnswer>

    @GET("mobileappusers/checktoken")
    fun checkToken(
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>



    @GET("mobileappusers/getuserdata")
    fun getUserData(
        @Query("token") token: String?
    ): Call<UserData>

    // for avatar
    @GET("mobileappusers/changedavatar")
    fun changedAvatar(
        @Query("choosen_parts") choosenParts: ArrayList<Int?>?,
        @Query("avatar_name") avatarName: String?,
        @Query("token") token: String?
    ): Call<SimpleServerAnswer>

    @GET("mobileappusers/checkavatarname")
    fun checkAvatarName(
        @Query("avatar_name") avatarName: String?
    ): Call<SimpleServerAnswer>


    @GET("mobileappusers/getamountofonetypeavatarparts")
    fun getAmountOfOneTypeAvatarParts(
        @Query("avatar_part_type") partNumber: Int?
    ): Call<SimpleServerAnswer>
}