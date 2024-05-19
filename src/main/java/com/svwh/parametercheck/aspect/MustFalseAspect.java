package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustFalse;
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
 * @Date 2024/5/19 15:08
 */
@Aspect
@Component
public class MustFalseAspect extends BaseAspect{

    @Pointcut("@annotation(mustFalse)")
    public void ano(MustFalse mustFalse) {}


    @Before(value = "ano(mustFalse)", argNames = "joinPoint,mustFalse")
    public void tokenVerify(JoinPoint joinPoint, MustFalse mustFalse) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            for (String key : mustFalse.keys()) {
                Object value = parser.parseExpression(key).getValue(context);
                if (Boolean.TRUE.equals(value)){
                    if (mustFalse.tipMessage().isEmpty()){
                        throw new ParamException("变量：" + key + " 必须为 false!");
                    }
                    throw new ParamException(mustFalse.tipMessage());
                }
            }

        }
    }

}
