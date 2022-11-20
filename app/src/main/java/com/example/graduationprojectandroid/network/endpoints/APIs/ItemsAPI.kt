package com.example.graduationprojectandroid.network.endpoints.APIs

import com.example.graduationprojectandroid.network.endpoints.ServerAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsAPI {
    @GET("/get_market_items")
    fun getMarketItems(
        @Query("token") token: String?
    ): Call<ServerAnswer>

    @GET("/get_inventory_items")
    fun getInventoryItems(
        @Query("token") token: String?
    ): Call<ServerAnswer>
}