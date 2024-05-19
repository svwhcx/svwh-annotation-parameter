package com.svwh.parametercheck.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 1:42
 */
public class BaseAspect {

    protected final ExpressionParser parser = new SpelExpressionParser();

    /**
     * 解析方法的参数
     * @param joinPoint
     * @return
     */
    protected Map<String, Object> resolveVariables(JoinPoint joinPoint){
        Map<String,Object> variables = new HashMap<>();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            variables.put(parameterNames[i], args[i]);
        }
        return variables;
    }

    /**
     * 解析配置上下文
     * @param variables
     * @return
     */
    protected EvaluationContext resolveContext(Map<String,Object> variables){
        EvaluationContext context = new StandardEvaluationContext();
        variables.forEach(context::setVariable);
        return context;
    }


}
