package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustTrue;
import com.svwh.parametercheck.exception.ParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 15:07
 */
@Aspect
@Component
public class MustTrueAspect extends BaseAspect{

    @Pointcut("@annotation(mustTrue)")
    public void ano(MustTrue mustTrue) {}


    @Before(value = "ano(mustTrue)", argNames = "joinPoint,mustTrue")
    public void tokenVerify(JoinPoint joinPoint, MustTrue mustTrue) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);

            for (String key : mustTrue.keys()) {
                ExpressionParser parser1 = new SpelExpressionParser();
                Object value = parser1.parseExpression(key).getValue(context);
                if (!(value instanceof Boolean)){
                    throw new ParamException("变量：" + key + " 不是布尔值");
                }
                if (Boolean.FALSE.equals(value)){
                    if (mustTrue.tipMessage().isEmpty()){
                        throw new ParamException("变量：" + key + " 必须为true");
                    }
                    throw new ParamException(mustTrue.tipMessage());
                }
            }

        }

    }

}
