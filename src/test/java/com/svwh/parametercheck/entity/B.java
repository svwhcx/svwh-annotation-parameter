package com.svwh.parametercheck.entity;

import java.util.List;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 17:54
 */
public class B {

    private int age;

    private List<String> list;

    private boolean isVip;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private C c;

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
