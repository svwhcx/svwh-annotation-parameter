package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.NotNull;
import com.svwh.parametercheck.exception.ParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/15 23:43
 */
@Aspect
@Component
public class NotNullAspect extends BaseAspect {



    @Pointcut("@annotation(myAnnotation)")
    public void callAt(NotNull myAnnotation) {}


    @Before(value = "callAt(notNull)", argNames = "joinPoint,notNull")
    public void tokenVerify(JoinPoint joinPoint,NotNull notNull) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            for (String key : notNull.keys()) {
                Object value = parser.parseExpression(key).getValue(context);
                if (value == null){
                    if (notNull.tipMessage().isEmpty()){
                        throw new ParamException("参数：" + key + ": 不能为null!");
                    }else{
                        throw new ParamException(notNull.tipMessage());
                    }
                }
            }

        }

    }

}
