package com.example.goorm_spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j // log 애노테이션
@Aspect // AOP 클래스 선언
@Component // Bean 등록
public class LoggingAspect {

    @Around("execution(* com.example.goorm_spring.service..*.*(..))") // 메서드 실행 전 후를 모두 제어하는 애노테이션
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName(); // 실행된 메서드 이름
        String className = joinPoint.getTarget().getClass().getSimpleName(); // 클래스 이름

        log.info("[시작] {}.{}()", className, methodName);
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // 실제 메서드 실행

        long end = System.currentTimeMillis();
        log.info("[완료] {}.{}() 실행시간: {}ms", className, methodName, (end - start));

        return result;
    }
}