package com.dengzi.retrofit;

import com.dengzi.retrofit.custom.DzRetrofit;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/14.
 * @Version:1.0.0
 */

public class DzRetrofitManager {
    String API_BASE_URL = "https://api.github.com";

    private static volatile DzRetrofitManager instance;
    private DzRetrofit mRetrofit;

    private DzRetrofitManager() {
        mRetrofit = new DzRetrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(com.dengzi.retrofit.OkHttpClient.getOkHttpBuilder().build())
                .build();
    }

    public static DzRetrofitManager getInstance() {
        if (instance == null) {
            synchronized (DzRetrofitManager.class) {
                if (instance == null) {
                    instance = new DzRetrofitManager();
                }
            }
        }
        return instance;
    }

    public DzRetrofit getClient() {
        return mRetrofit;
    }
}
