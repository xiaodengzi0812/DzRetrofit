package com.dengzi.retrofit.custom;

/**
 * @author Djk
 * @Title: 自定义回调接口
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public interface DzCallback<T> {
    void onResponse(DzCall<T> call, DzResponse<T> response);

    void onFailure(DzCall<T> call, Throwable t);
}
