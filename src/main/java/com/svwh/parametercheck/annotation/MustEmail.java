package com.svwh.parametercheck.annotation;

import javax.xml.bind.annotation.XmlType;
import java.lang.annotation.*;

/**
 * 元素值的格式必须是email，可以指定具体的email格式
 * @description
 * @Author cxk
 * @Date 2024/5/19 13:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MustEmails.class)
public @interface MustEmail {


    String key() default "";

    /**
     *
     * @return 邮箱格式正则
     */
    String regex() default "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    String tipMessage() default "";
}
