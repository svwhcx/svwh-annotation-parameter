package com.svwh.parametercheck.annotation;

import java.lang.annotation.*;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 22:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface MustMins {

    MustMin[] value();

}
