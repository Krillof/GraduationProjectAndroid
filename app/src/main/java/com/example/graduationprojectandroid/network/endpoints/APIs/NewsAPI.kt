package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @get:GET("/get_news")
    val news: Call<SimpleServerAnswer>

    @GET("/get_news_item")
    fun getNewsItem(
        @Query("news_item_id") newsItemId: Int
    ): Call<SimpleServerAnswer>
}