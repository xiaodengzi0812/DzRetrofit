package com.dengzi.retrofit;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */

public class OkHttpClient {

    static {
        okHttpBuilder = new okhttp3.OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("dengzi", "message = " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .connectTimeout(20, TimeUnit.SECONDS);
    }

    private static okhttp3.OkHttpClient.Builder okHttpBuilder;

    public static okhttp3.OkHttpClient.Builder getOkHttpBuilder() {
        return okHttpBuilder;
    }

}
