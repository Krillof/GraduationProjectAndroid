package com.example.graduationprojectandroid.network.endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    //@GET("/posts/{id}")
    //public Call<Test> getPostWithID(@Path("id") int id);

    @GET("/")
    public Call<Test> test();
}
