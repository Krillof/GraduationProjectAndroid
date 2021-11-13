package com.example.graduationprojectandroid.network;

import com.example.graduationprojectandroid.network.endpoints.JSONPlaceHolderApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    //singleton
    private static NetworkService mInstance;

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }
    //singleton

    //Retrofit
    private static final String BASE_URL = "https://graduationprojectdo.azurewebsites.net/";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //      endpoints

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

    //      endpoints

    //Retrofit
}
