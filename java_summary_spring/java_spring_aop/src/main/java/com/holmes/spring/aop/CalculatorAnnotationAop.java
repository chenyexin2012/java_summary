package com.holmes.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 通过注解配置AOP
 *
 * @author Administrator
 */
@Component
@Aspect
@Order(1)
public class CalculatorAnnotationAop {
//public class CalculatorAnnotationAop implements Ordered {

    private final static String PRE = "[CalculatorAnnotationAop]";

//    /**
//     * 指定AOP的执行顺序
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return 1;
//    }


    /**
     * 统一配置切面
     */
    @Pointcut("execution(* com.holmes.spring.aop.Calculator.*(..))")
    public void pointcut() {
    }

    /**
     * 方法执行前通知
     *
     * @param point
     */
    @Before("pointcut()")
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
    @AfterReturning("pointcut()")
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
    @AfterThrowing("pointcut()")
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
    @After("pointcut()")
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
    @Around("pointcut()")
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
