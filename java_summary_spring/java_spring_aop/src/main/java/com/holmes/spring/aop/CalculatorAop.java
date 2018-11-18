package com.holmes.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

public class CalculatorAop {

    private final static String PRE = "[CalculatorAop]";

    /**
     * 方法执行前通知
     *
     * @param point
     */
    public void beforeMethod(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = className + "#" + point.getSignature().getName();
        System.out.println(PRE + "before " + methodName + " execute...");
    }

    /**
     * 目标方法返回后通知
     *
     * @param point
     * @see ExecutionTimeLoggingAop#afterReturning(Object, Method, Object[], Object)
     */
    public void afterReturning(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = className + "#" + point.getSignature().getName();
        System.out.println(PRE + "after " + methodName + " returning...");
    }

    /**
     * 方法出现异常时通知
     *
     * @param point
     * @see ExecutionTimeLoggingAop#afterThrowing(Method, Object[], Object, Exception)
     */
    public void afterThrowing(JoinPoint point) {

        String className = point.getTarget().getClass().getName();
        String methodName = className + "#" + point.getSignature().getName();
        System.out.println(PRE + methodName + " throwing exception...");
    }

    /**
     * 无论是否发生异常，都会执行通知
     *
     * @param point
     */
    public void afterMethod(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = className + "#" + point.getSignature().getName();
        System.out.println(PRE + "after " + methodName + " execute...");
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = className + "#" + joinPoint.getSignature().getName();
        System.out.println(PRE + "[Around]before " + methodName + " execute...");

        // 执行目标方法
        Object result = null;
        result = joinPoint.proceed();

        System.out.println(PRE + "[Around]after " + methodName + " execute...");

        // 目标方法的返回值
        return result;
    }
}
