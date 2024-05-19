package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.NotEmpty;
import com.svwh.parametercheck.exception.ParamException;
import com.svwh.parametercheck.util.CollectionUtil;
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
 * @Date 2024/5/19 1:41
 */
@Aspect
@Component
public class NotEmptyAspect extends BaseAspect{

    @Pointcut("@annotation(notEmpty)")
    public void ano(NotEmpty notEmpty) {}


    @Before(value = "ano(notEmpty)", argNames = "joinPoint,notEmpty")
    public void tokenVerify(JoinPoint joinPoint, NotEmpty notEmpty) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext evaluationContext = resolveContext(variables);
            String[] keys = notEmpty.keys();
            for (String key : keys) {
                Object value = super.parser.parseExpression(key).getValue(evaluationContext);
                if (CollectionUtil.isEmpty(value)){
                    if (notEmpty.tipMessage().isEmpty()){
                        throw new ParamException("参数:" + key.substring(1) + "不能为空");
                    }else{
                        throw new ParamException(notEmpty.tipMessage());
                    }
                }
            }
        }

    }

}
