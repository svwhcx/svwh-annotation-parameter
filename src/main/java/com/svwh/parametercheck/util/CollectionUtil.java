package com.svwh.parametercheck.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;


/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 1:48
 */
public class CollectionUtil {

    public static boolean isEmpty(Object object) {
        if (object == null) return true;
        if (object instanceof String){
            return ((String) object).isEmpty();
        }else if (object instanceof Collection){
            return ((Collection<?>) object).isEmpty();
        }else if (object instanceof Map){
            return ((Map<?, ?>) object).isEmpty();
        }else if (object.getClass().isArray()){
            return Array.getLength(object) == 0;
        }
        return false;
    }

    /**
     * 判断是否是空，对于字符串，需要去掉首尾的空格，然后进行判断
     * @param object
     * @return
     */
    public static boolean isBlank(Object object){
        if (object instanceof String){
            return isEmpty(((String) object).trim());
        }else{
            return isEmpty(object);
        }
    }



}
