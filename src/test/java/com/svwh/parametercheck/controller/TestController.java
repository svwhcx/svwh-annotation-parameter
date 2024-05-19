package com.svwh.parametercheck.controller;

import com.svwh.parametercheck.annotation.*;
import com.svwh.parametercheck.entity.A;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 17:19
 */
@Component
public class TestController {

    @NotNull(keys = {"#string","#a.name"})
    public String testNotNull(String string, A a){
        return string;
    }

    @NotEmpty(keys = {"#a.b.list"})
    public String testNotEmpty(A a){
        return "this is a test";
    }

    @NotBlank(keys = {"#a.name"})
    public String testNotBlank(A a){
        return "this is a test";
    }

    @MustTrue(keys = {"#a.b.isVip"})
    public String  testMustTrue(A a){
        return "this is a test";
    }

    @MustFalse(keys = {"#a.b.isVip"})
    public String testMustFalse(A a){
        return "this is a test";
    }

    @MustMax(key = "#a.b.age",value = 0)
    public String testMustMax(A a){
        return "this is a test";
    }

//    @MustMin(key = "#a.b.age",value = 200)
    @MustMin(key = "#a.age", value = 100)
    public String testMustMin(A a){
        return "this is a test";
    }

    @MustFuture(keys = {"#a.localDateTime"})
    public String testMustFuture(A a){
        return "this is a test";
    }

    @MustPast(keys = {"#a.b.c.dateTime","#a.updateTime"})
    public String testMustPast(A a){
        return "this is a test";
    }


    @MustEmail(key = "#a.b.email")
    public String testMustEmail(A a){
        return "this is a test";
    }

    @MustLength(key = "#a.name",min = 1,max = 10)
    public String testMustLength(A a){
        return "this is a test";
    }

    @MustPattern(keys = {"#a.name"},regex = "[")
    public String testMustPatter(A a) {
        return "this is a test";
    }
}
