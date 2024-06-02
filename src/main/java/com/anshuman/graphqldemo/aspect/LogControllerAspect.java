package com.anshuman.graphqldemo.aspect;

import com.anshuman.graphqldemo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LogControllerAspect {

    @Around("execution(public * com.anshuman.graphqldemo.resource.controller.*.*(..))")
    public Object controllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return loggingMethod(joinPoint, "controller");
    }

    @Around("this(org.springframework.data.jpa.repository.JpaRepository)")
    public Object repositoryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return loggingMethod(joinPoint, "repository");
    }


    private static Object loggingMethod(ProceedingJoinPoint joinPoint, String from) throws Throwable {
        Instant start = Instant.now();
        Object returnObject = joinPoint.proceed();
        Instant end = Instant.now();
        String methodName = joinPoint.getSignature().getName();

        log.debug(from + "Method: " + methodName
                + " args: {" + getArguments(joinPoint) + "}"
                + ", running on Thread{type: " + (Thread.currentThread().isVirtual() ? "Virtual" : "Platform")+ ", name: "
                + Thread.currentThread().getName() + "}"
                + ", took " + (end.toEpochMilli() - start.toEpochMilli()) + " ms");
        return returnObject;
    }

    private static String getArguments(ProceedingJoinPoint joinPoint) {
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(paramNames[i]).append(": ").append(StringUtil.truncate(args[i], 30));
        }
        return builder.toString();
    }
}
