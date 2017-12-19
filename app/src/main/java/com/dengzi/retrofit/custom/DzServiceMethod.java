package com.dengzi.retrofit.custom;

import com.dengzi.retrofit.custom.http.DzGet;
import com.dengzi.retrofit.custom.http.DzPath;
import com.dengzi.retrofit.custom.http.DzPost;
import com.dengzi.retrofit.custom.http.DzQuery;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author Djk
 * @Title: 方法解析器
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
public class DzServiceMethod {
    DzRetrofit dzRetrofit;
    Method method;
    String httpMethod;
    String relativeUrl;
    DzParameterHandler<?>[] parameterHandlers;

    public DzServiceMethod(Builder builder) {
        this.dzRetrofit = builder.dzRetrofit;
        this.method = builder.method;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.parameterHandlers = builder.parameterHandlers;
    }

    public String getUrl() {
        return dzRetrofit.baseUrl + relativeUrl;
    }

    public <T> T parseBody(ResponseBody body) {
        // 获取解析类型 T 获取方法返回值的类型
        Type returnType = method.getGenericReturnType();// 返回值对象
        Class<T> dataClass = (Class<T>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
        Gson gson = new Gson();
        try {
            return gson.fromJson(body.string(), dataClass);
        } catch (IOException e) {
        }
        return null;
    }

    public static class Builder {
        DzRetrofit dzRetrofit;
        Method method;
        final Annotation[] methodAnnotations;
        String httpMethod;
        String relativeUrl;
        Annotation[][] parameterAnnotations;
        final DzParameterHandler<?>[] parameterHandlers;

        public Builder(DzRetrofit dzRetrofit, Method method) {
            this.dzRetrofit = dzRetrofit;
            this.method = method;
            // 获取方法上面的注解,例如: @GET("/users/{user}")
            methodAnnotations = method.getAnnotations();
            // 获取方法参数注解,例如: @Path("user") String userName
            parameterAnnotations = method.getParameterAnnotations();
            // 创建一个参数集合,存放解析好的参数
            parameterHandlers = new DzParameterHandler[parameterAnnotations.length];
        }

        public DzServiceMethod build() {
            // 解析方法上面的注解
            executeMethodAnnotations();
            // 解析方法里面的注解
            executeParameterAnnotations();
            return new DzServiceMethod(this);
        }

        /**
         * 解析方法上面的注解
         *
         * @GET("/users/{user}")
         */
        private void executeMethodAnnotations() {
            for (Annotation methodAnnotation : methodAnnotations) {
                // 如果是Get请求
                if (methodAnnotation instanceof DzGet) {
                    httpMethod = "Get";
                    relativeUrl = ((DzGet) methodAnnotation).value();
                } else if (methodAnnotation instanceof DzPost) {
                    httpMethod = "Post";
                    relativeUrl = ((DzPost) methodAnnotation).value();
                }
            }
        }

        /**
         * 解析方法里面的注解
         *
         * @Path("user") String userName
         */
        private void executeParameterAnnotations() {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                if (parameterAnnotation != null) {
                    Annotation annotation = parameterAnnotation[0];
                    if (annotation instanceof DzQuery) {
                        parameterHandlers[i] = new DzParameterHandler.DzQuery<>(((DzQuery) annotation).value());
                    } else if (annotation instanceof DzPath) {
                        parameterHandlers[i] = new DzParameterHandler.DzPath<>(((DzPath) annotation).value());
                    }
                }
            }
        }
    }
}
