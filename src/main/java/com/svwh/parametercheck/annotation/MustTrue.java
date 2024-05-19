package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MustTrue {

    String[] keys() default {};

    String tipMessage() default "";

}
