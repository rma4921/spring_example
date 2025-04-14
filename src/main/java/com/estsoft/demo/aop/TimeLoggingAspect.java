package com.estsoft.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeLoggingAspect {
    // Pointcut
    @Pointcut("execution(* com.estsoft.demo.blog.service..*(..))") // (..) -> 0개 이상의 메소드
    public void pointcut() {}

    // Advice
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 시간 측정
        long startTimeMs = System.currentTimeMillis();

        try { // 핵심 로직 실행
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            // 시간 측정
            long endTimeMs = System.currentTimeMillis();
            log.info("메서드 실행 시간: {}ms", endTimeMs - startTimeMs);
        }
    }
}
