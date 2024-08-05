package com.anshuman.graphqldemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(public * com.anshuman.graphqldemo.resource.controller.*.*(..))")
    public Object controllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return loggingMethod(joinPoint, "Controller");
    }

    @Around("this(org.springframework.data.repository.CrudRepository)")
    public Object crudRepositoryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return loggingMethod(joinPoint, "CrudRepository");
    }

    @Around("this(org.springframework.data.jpa.repository.JpaRepository)")
    public Object jpaRepositoryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return loggingMethod(joinPoint, "JpaRepository");
    }

    private static Object loggingMethod(ProceedingJoinPoint joinPoint, String from) throws Throwable {
        Instant start = Instant.now();
        Object returnObject = joinPoint.proceed();
        Instant end = Instant.now();
        String className = Optional.ofNullable(joinPoint.getTarget())
                .flatMap(target -> from.contains("Repository") ? Optional.ofNullable(AopProxyUtils.proxiedUserInterfaces(target)[0]) :
                        Optional.of(target.getClass()))
                .map(Class::getSimpleName)
                .orElse("n/a");

        String methodName = joinPoint.getSignature().getName();

        log.debug("{}.{}, args: {{}}, running on {}, took {} ms", className, methodName, getMethodArgs(joinPoint),
                Thread.currentThread().isVirtual() ? "Virtual thread with id: " +
                Thread.currentThread().threadId() : "Platform thread with name: " + Thread.currentThread().getName(),
                end.toEpochMilli() - start.toEpochMilli());
        return returnObject;
    }

    private static String getMethodArgs(ProceedingJoinPoint joinPoint) {
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return "";
        }

        Map<String, String> argMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            String argStr = Optional.ofNullable(args[i])
                    .map(Object::toString)
                    .filter(arg -> !arg.isBlank())
                    .map(String::trim)
                    .orElse("");
            argMap.put(paramNames[i], argStr);
        }
        return argMap.entrySet()
                .stream().map(entry -> entry.getKey() + ": " +  entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
