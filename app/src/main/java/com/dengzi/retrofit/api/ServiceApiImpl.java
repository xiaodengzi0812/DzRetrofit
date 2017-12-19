package com.dengzi.retrofit.api;

import com.dengzi.retrofit.RetrofitManager;
import com.dengzi.retrofit.DzRetrofitManager;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/14.
 * @Version:1.0.0
 */
public class ServiceApiImpl {
    public static ServiceApi creatApi() {
        return RetrofitManager.getInstance().getClient()
                .create(ServiceApi.class);
    }

    public static ServiceApi creatApi2() {
        return DzRetrofitManager.getInstance().getClient()
                .create(ServiceApi.class);
    }
}
