package com.svwh.parametercheck.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 关于变量不能为空的注解
 * @description
 * @Author cxk
 * @Date 2024/5/19 1:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    /**
     *
     * @return 哪些参数不能为空
     */
    String[] keys();

    /**
     * @return 变量为空的前端的提示信息
     */
    String tipMessage() default "";


}
