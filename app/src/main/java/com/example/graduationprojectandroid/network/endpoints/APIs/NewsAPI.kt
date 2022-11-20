package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.endpoints.ServerAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/get_news")
    fun getNews(): Call<ServerAnswer>

    @GET("/get_news_item")
    fun getNewsItem(
        @Query("news_item_id") newsItemId: Int
    ): Call<ServerAnswer>
}