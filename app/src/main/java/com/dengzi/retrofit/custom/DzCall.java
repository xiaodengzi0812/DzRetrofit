package com.dengzi.retrofit.custom;

/**
 * @author Djk
 * @Title: 自定义请求接口
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public interface DzCall<T> {
    void enqueue(DzCallback<T> callback);
}
