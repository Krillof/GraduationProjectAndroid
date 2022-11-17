package com.example.graduationprojectandroid.network.endpoints.APIs;

import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("/get_news")
    Call<SimpleServerAnswer> getNews();

    @GET("/get_news_item")
    Call<SimpleServerAnswer> getNewsItem(
            @Query("news_item_id") int newsItemId
    );
}
