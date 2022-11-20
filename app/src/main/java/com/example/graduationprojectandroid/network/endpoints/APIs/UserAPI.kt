package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.endpoints.ServerAnswer
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList

interface UserAPI {

    @GET("mobileappusers/tryenter")
    fun test(
        @Query("login") login: String?,
        @Query("password") password: String?
    ): Call<String>

    @GET("mobileappusers/tryregister")
    fun tryRegister(
        @Query("login") login: String?,
        @Query("password") password: String?
    ): Call<ServerAnswer>

    @GET("mobileappusers/tryenter")
    fun tryEnter(
        @Query("login") login: String?,
        @Query("password") password: String?
    ): Call<ServerAnswer>

    @GET("mobileappusers/checktoken")
    fun checkToken(
        @Query("login") login: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>



    @GET("mobileappusers/getuserdata")
    fun getUserData(
        @Query("token") token: String?
    ): Call<ServerAnswer>

    // for avatar
    @GET("mobileappusers/changedavatar")
    fun changedAvatar(
        @Query("choosen_parts") choosenParts: ArrayList<Int?>?,
        @Query("avatar_name") avatarName: String?,
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @GET("mobileappusers/checkavatarname")
    fun checkAvatarName(
        @Query("avatar_name") avatarName: String?
    ): Call<ServerAnswer>


    @GET("mobileappusers/getamountofonetypeavatarparts")
    fun getAmountOfOneTypeAvatarParts(
        @Query("avatar_part_type") partNumber: Int?
    ): Call<ServerAnswer>
}