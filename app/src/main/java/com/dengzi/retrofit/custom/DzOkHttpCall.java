package com.dengzi.retrofit.custom;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * @author Djk
 * @Title: 真实的请求
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public class DzOkHttpCall<T> implements DzCall<T> {
    DzServiceMethod serviceMethod;
    Object[] args;
    DzRequestBuilder requestBuilder;

    public DzOkHttpCall(DzServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
        // 创建一个参数创建者,用来设置参数
        requestBuilder = new DzRequestBuilder(serviceMethod.getUrl(), serviceMethod.httpMethod, serviceMethod.parameterHandlers, args);
    }

    @Override
    public void enqueue(final DzCallback<T> dzCallback) {
        // 获取之前传入的请求client
        OkHttpClient httpClient = serviceMethod.dzRetrofit.okhttpClient;
        // 真正的请求
        httpClient.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (dzCallback != null) {
                    dzCallback.onFailure(DzOkHttpCall.this, e);
                }
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                DzResponse rResponse = new DzResponse();
                rResponse.body = serviceMethod.parseBody(response.body());
                if (dzCallback != null) {
                    dzCallback.onResponse(DzOkHttpCall.this, rResponse);
                }
            }
        });
    }
}
