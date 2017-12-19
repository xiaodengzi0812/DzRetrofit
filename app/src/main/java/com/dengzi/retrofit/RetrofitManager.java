package com.dengzi.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/14.
 * @Version:1.0.0
 */

public class RetrofitManager {
    String API_BASE_URL = "https://api.github.com";

    private static volatile RetrofitManager instance;
    private Retrofit mRetrofit;

    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(com.dengzi.retrofit.OkHttpClient.getOkHttpBuilder().build())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public Retrofit getClient() {
        return mRetrofit;
    }
}
