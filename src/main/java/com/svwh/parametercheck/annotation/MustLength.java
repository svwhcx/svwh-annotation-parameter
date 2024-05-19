package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符长度必须限制在min和max之间
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustLength {

    String key() default "";

    /**
     *
     * @return 最小长度
     */
    int min() default 0;

    /**
     *
     * @return 最大长度
     */
    int max() default Integer.MAX_VALUE;

    String tipMessage() default "";

}
