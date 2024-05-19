package com.svwh.parametercheck.exception;


/**
 * @description 参数错误异常
 * @Author cxk
 * @Date 2022/4/30 19:36
 */
public class ParamException extends RuntimeException{

    public ParamException(){
        super();
    }



    public ParamException(String msg){
        super(msg);
    }

}
