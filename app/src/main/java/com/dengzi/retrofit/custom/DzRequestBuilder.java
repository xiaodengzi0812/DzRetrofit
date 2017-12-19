package com.dengzi.retrofit.custom;

import android.util.Log;

import okhttp3.Request;

/**
 * @author Djk
 * @Title: 参数创建者
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public class DzRequestBuilder {
    DzParameterHandler<Object>[] parameterHandlers;
    Object[] args;
    String url;

    public DzRequestBuilder(String url, String httpMethod, DzParameterHandler<?>[] parameterHandlers, Object[] args) {
        this.parameterHandlers = (DzParameterHandler<Object>[]) parameterHandlers;
        this.args = args;
        this.url = url;
    }

    /**
     * 创建一个请求Requst
     *
     * @return
     */
    public Request build() {
        int count = args.length;
        for (int i = 0; i < count; i++) {
            parameterHandlers[i].apply(this, args[i]);
        }
        Request request = new Request.Builder().url(url).build();
        return request;
    }

    /**
     * 拼接参数
     *
     * @param key
     * @param value
     */
    public void addQueryName(String key, String value) {
        String addStr;
        if (url.contains("?")) {
            addStr = "&";
        } else {
            addStr = "?";
        }
        url = url + addStr + key + "=" + value;
    }

    /**
     * 占位符替换
     *
     * @param key
     * @param value
     */
    public void addPathSegment(String key, String value) {
        String keyStr = "{" + key + "}";
        url = url.replace(keyStr, value);
    }
}
