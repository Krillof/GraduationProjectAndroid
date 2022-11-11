package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemsAPI {
    @GET("/get_market_items")
    Call<SimpleServerAnswer> getMarketItems(
            @Query("token") String token
    );

    @GET("/get_inventory_items")
    Call<SimpleServerAnswer> getInventoryItems(
            @Query("token") String token
    );
}
