package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 必须符合指定的正则表达式
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustPattern {

    String[] keys() default {};

    /**
     *
     * @return 正则
     */
    String regex() default "";

    String tipMessage() default "";

}
