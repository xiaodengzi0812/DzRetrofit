package com.dengzi.retrofit.api;


import com.dengzi.retrofit.bean.UserBean;
import com.dengzi.retrofit.custom.DzCall;
import com.dengzi.retrofit.custom.http.DzGet;
import com.dengzi.retrofit.custom.http.DzPath;
import com.dengzi.retrofit.custom.http.DzQuery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/14.
 * @Version:1.0.0
 */
public interface ServiceApi {
    @GET("/users/{user}")
    Call<UserBean> getInfo(
            @Path("user") String userName,
            @Query("sort") String sort
    );

    @DzGet("/users/{user}")
    DzCall<UserBean> getInfo2(
            @DzPath("user") String userName,
            @DzQuery("sort") String sort
    );
}
