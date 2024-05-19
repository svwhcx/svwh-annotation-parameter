package com.svwh.parametercheck.aspect;

import com.svwh.parametercheck.annotation.MustPast;
import com.svwh.parametercheck.exception.ParamException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @description
 * @Author cxk
 * @Date 2024/5/19 15:07
 */
@Aspect
@Component
public class MustPastAspect extends BaseAspect{

    @Pointcut("@annotation(mustPast)")
    public void ano(MustPast mustPast) {}


    @Before(value = "ano(mustPast)", argNames = "joinPoint,mustPast")
    public void tokenVerify(JoinPoint joinPoint, MustPast mustPast) throws ParseException {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature){
            Map<String, Object> variables = resolveVariables(joinPoint);
            EvaluationContext context = resolveContext(variables);
            String[] keys = mustPast.keys();
            for (String key : keys) {
                Object value = parser.parseExpression(key).getValue(context);
                if (value instanceof Date){
                    Date dateValue = (Date) value;
                    if (dateValue.after(new Date())){
                        if (mustPast.tipMessage().isEmpty()){
                            throw new ParamException("参数：" + key + " 必须为过去时间");
                        }
                        throw new ParamException(mustPast.tipMessage());
                    }
                }else if (value instanceof LocalDateTime){
                    LocalDateTime localDateTimeValue = (LocalDateTime) value;
                    if (localDateTimeValue.isAfter(LocalDateTime.now())){
                        if (mustPast.tipMessage().isEmpty()){
                            throw new ParamException("参数：" + key + " 必须为过去时间");
                        }
                        throw new ParamException(mustPast.tipMessage());
                    }
                }else if (value instanceof String){
                    String stringDateValue = (String) value;
                    // 根据日期格式判断
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mustPast.dateRegex());
                    if (simpleDateFormat.parse(stringDateValue).after(new Date())){
                        if (mustPast.tipMessage().isEmpty()){
                            throw new ParamException("参数：" + key + " 必须为过去时间");
                        }
                        throw new ParamException(mustPast.tipMessage());
                    }
                }
            }
        }
    }

}
