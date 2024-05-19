package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证元素的值不为空，包括
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {

    String[] keys() default {};

    String tipMessage() default "";

}
