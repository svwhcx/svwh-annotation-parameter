package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustPattern;
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
import java.util.regex.PatternSyntaxException;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 15:20
 */
@Aspect
@Component
public class MustPatternAspect extends BaseAspect{

    @Pointcut("@annotation(mustPattern)")
    public void ano(MustPattern mustPattern) {}


    @Before(value = "ano(mustPattern)", argNames = "joinPoint,mustPattern")
    public void tokenVerify(JoinPoint joinPoint, MustPattern mustPattern) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            for (String key : mustPattern.keys()) {
                Object value = parser.parseExpression(key).getValue(context);
                // 要判断是否是字符串
                if (value instanceof String){
                    String stringValue = (String) value;
                    if (mustPattern.regex().isEmpty()){
                        throw new RuntimeException("没有配置规则!");
                    }
                    // 进行邮箱校验检测
                    try {
                        if (!stringValue.matches(mustPattern.regex())){
                            if (mustPattern.tipMessage().isEmpty()){
                                throw new ParamException("参数：" + key + " 格式错误!");
                            }
                            throw new ParamException(mustPattern.tipMessage());
                        }
                    }catch (PatternSyntaxException e){
                        throw new RuntimeException("正则表达式配置错误!");
                    }
                }
            }

        }
    }

}
