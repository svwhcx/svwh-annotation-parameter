package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustEmail;
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
 * @Date 2024/5/19 15:14
 */
@Aspect
@Component
public class MustEmailAspect extends BaseAspect{


    @Before("@annotation(com.svwh.parametercheck.annotation.MustEmail) || @annotation(com.svwh.parametercheck.annotation.MustEmails)")
    public void tokenVerify(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            Method method = ((MethodSignature) signature).getMethod();
            MustEmail[] annotations = method.getAnnotationsByType(MustEmail.class);
            for (MustEmail mustEmail : annotations) {
                String key = mustEmail.key();
                if (!key.isEmpty()){
                    Object value = parser.parseExpression(key).getValue(context);
                    // 要判断是否是字符串
                    if (value instanceof String){
                        String stringValue = (String) value;
                        // 进行邮箱校验检测
                        if (!stringValue.matches(mustEmail.regex())){
                            if (mustEmail.tipMessage().isEmpty()){
                                throw new ParamException("参数：" + key + " 邮箱格式错误!");
                            }
                            throw new ParamException(mustEmail.tipMessage());
                        }
                    }
                }
            }

        }
    }

}
