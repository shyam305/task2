package com.centime.task2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Aspect
@Component
@Slf4j
public class LogMethodParamAspect {

    @Before("@annotation(logMethodParam)")
    public void logMethodParams(JoinPoint joinPoint, LogMethodParam logMethodParam) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // Log the method name and parameters
        log.info("Method: " + methodName + " | Parameters: " + arrayToString(args));
    }

    private String arrayToString(Object[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

}
