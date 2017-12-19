package com.dengzi.retrofit.custom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import okhttp3.OkHttpClient;

/**
 * @author Djk
 * @Title: 自己实现的Retrofit
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public class DzRetrofit {
    String baseUrl;
    OkHttpClient okhttpClient;
    private ConcurrentMap<Method, DzServiceMethod> serviceMethodMapCache;

    public DzRetrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.okhttpClient = builder.okhttpClient;
        serviceMethodMapCache = new ConcurrentHashMap<>();
    }

    public <T> T create(Class<T> service) {
        // 动态代理创建
        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        method.getParameterTypes();
                        // 解析参数注解
                        DzServiceMethod serviceMethod = loadServiceMethod(method);
                        // 封装call
                        DzOkHttpCall call = new DzOkHttpCall(serviceMethod, args);
                        // 返回一个封装好的call
                        return call;
                    }
                });
    }

    private DzServiceMethod loadServiceMethod(Method method) {
        // 享元设计模式
        DzServiceMethod serviceMethod = serviceMethodMapCache.get(method);
        if (serviceMethod == null) {
            serviceMethod = new DzServiceMethod.Builder(this, method).build();
            serviceMethodMapCache.put(method, serviceMethod);
        }
        return serviceMethod;
    }

    public static class Builder {
        String baseUrl;
        OkHttpClient okhttpClient;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(OkHttpClient okhttpClient) {
            this.okhttpClient = okhttpClient;
            return this;
        }

        public DzRetrofit build() {
            return new DzRetrofit(this);
        }
    }
}
