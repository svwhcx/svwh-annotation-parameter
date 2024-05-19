package com.svwh.parametercheck.annotation;

import java.lang.annotation.*;

/**
 * 必须为一个不大于某个值的数
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MustMins.class)
public @interface MustMin {

    String key() default "";

    int value() default Integer.MAX_VALUE;

    String tipMessage() default "";

}
