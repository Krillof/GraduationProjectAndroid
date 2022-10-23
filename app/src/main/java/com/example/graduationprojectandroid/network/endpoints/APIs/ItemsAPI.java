package com.example.graduationprojectandroid.network.endpoints.APIs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemsAPI {
    @GET("/get_market_items")
    Call<String> getMarketItems(
            @Query("token") String token
    );

    @GET("/get_inventory_items")
    Call<String> getInventoryItems(
            @Query("token") String token
    );
}
