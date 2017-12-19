package com.dengzi.retrofit.custom.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/19.
 * @Version:1.0.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DzPath {
    String value();
}
