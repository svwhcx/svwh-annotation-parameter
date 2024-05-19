package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustLength;
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
public class MustLengthAspect extends BaseAspect{


    @Before("@annotation(com.svwh.parametercheck.annotation.MustLength) || @annotation(com.svwh.parametercheck.annotation.MustLengths)")
    public void tokenVerify(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            Method method = ((MethodSignature) signature).getMethod();
            MustLength[] annotations = method.getAnnotationsByType(MustLength.class);
            for (MustLength mustLength : annotations) {
                String key = mustLength.key();
                Object value = parser.parseExpression(key).getValue(context);
                if (value instanceof String){
                    String stringValue = (String) value;
                    int length = stringValue.length();
                    if (length < mustLength.min() || length > mustLength.max()){
                        if (mustLength.tipMessage().isEmpty()){
                            throw new ParamException("参数：" + key + " 长度不符合要求!");
                        }
                        throw new ParamException(mustLength.tipMessage());
                    }
                }
            }
        }
    }

}
