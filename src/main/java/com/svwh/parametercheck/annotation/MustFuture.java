package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个日期必须是一个将来时间
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustFuture {

    String[] keys() default "";

    /**
     *
     * @return 日期格式正则
     */
    String dateRegex() default "yyyy-MM-dd HH:mm:ss";

    String tipMessage() default "";
}
