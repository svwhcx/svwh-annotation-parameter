package com.svwh.parametercheck.annotation;

import java.lang.annotation.*;

/**
 * 必须是不小于一个数的值
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MustMaxs.class)
public @interface MustMax {

    String key() default "";

    int value() default Integer.MIN_VALUE;

    String tipMessage() default "";

}
