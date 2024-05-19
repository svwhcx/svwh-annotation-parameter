package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustMin;
import com.svwh.parametercheck.exception.ParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 15:08
 */
@Aspect
@Component
public class MustMinAspect extends BaseAspect {

    @Before("@annotation(com.svwh.parametercheck.annotation.MustMin) || @annotation(com.svwh.parametercheck.annotation.MustMins)")
    public void tokenVerify(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            Method method = ((MethodSignature) signature).getMethod();
            MustMin[] annotations = method.getAnnotationsByType(MustMin.class);
            for (MustMin mustmin : annotations) {
                String key = mustmin.key();
                if (!key.isEmpty()){
                    Object value = parser.parseExpression(key).getValue(context);
                    if (value instanceof Integer){
                        Integer intValue = (Integer) value;
                        if (intValue > mustmin.value()){
                            if (mustmin.tipMessage().isEmpty()){
                                throw new ParamException("参数：" + key.substring(1) + "不能大于: " + mustmin.value());
                            }
                            throw new ParamException(mustmin.tipMessage());
                        }
                    }
                }
            }
        }
    }
}
