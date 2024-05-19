package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个日期必须是一个过去的日期
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustPast {

    String[] keys() default {};

    /**
     * 日期格式化正则
     * @return
     */
    String dateRegex() default "yyyy-MM-dd HH:mm:ss";

    String tipMessage() default "";

}
