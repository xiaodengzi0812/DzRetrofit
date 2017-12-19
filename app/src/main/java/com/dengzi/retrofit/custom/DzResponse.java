package com.dengzi.retrofit.custom;

/**
 * @author Djk
 * @Title: 回调参数封装
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public class DzResponse<T> {
    public T body;

    public T body() {
        return body;
    }
}
