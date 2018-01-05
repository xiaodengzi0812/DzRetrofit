package com.dengzi.retrofit.rxjava;


import com.dengzi.retrofit.bean.UserBean;
import com.dengzi.retrofit.custom.DzCall;
import com.dengzi.retrofit.custom.http.DzGet;
import com.dengzi.retrofit.custom.http.DzPath;
import com.dengzi.retrofit.custom.http.DzQuery;

import io.reactivex.Observable;
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
public interface GithubApi {

    @GET("/users/{user}")
    Observable<UserBean> getInfo(
            @Path("user") String userName,
            @Query("sort") String sort
    );

    @GET("/users/{user}")
    Observable<UserBean> getInfo2(
            @Path("user") String userName,
            @Query("sort") String sort
    );
}
