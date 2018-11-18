package com.holmes.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class CalculatorAop {

    /**
     * 方法执行前通知
     * @param point
     */
    public void beforeMethod(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = className + "#" + point.getSignature().getName();
        System.out.println("before " + methodName + " execute...");
    }

    /**
     * 无论是否发生异常，都会执行通知
     * @param point
     */
    public void afterMethod(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = className + "#" + point.getSignature().getName();
        System.out.println("after " + methodName + " execute...");
    }

    /**
     * 环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = className + "#" + joinPoint.getSignature().getName();
        System.out.println("before " + methodName + " execute...");

        // 执行目标方法
        Object result = joinPoint.proceed();

        System.out.println("after " + methodName + " execute...");

        // 目标方法的返回值
        return result;
    }
}
