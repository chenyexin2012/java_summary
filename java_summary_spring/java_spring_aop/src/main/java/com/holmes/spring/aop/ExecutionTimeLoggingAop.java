package com.holmes.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutionTimeLoggingAop implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private final static String PRE = "[ExecutionTimeLoggingAop]";
    /**
     * 方法执行前通知
     *
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

        String methodName = method.getDeclaringClass().getName() + "#" + method.getName();
        System.out.println(PRE + "before " + methodName + " execute["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS").format(new Date()) + "]");
    }

    /**
     * 方法执行结束返回后通知
     *
     * @param returnValue
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        String methodName = method.getDeclaringClass().getName() + "#" + method.getName();
        System.out.println(PRE + "after " + methodName + " returning["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS").format(new Date()) + "]");
    }

    /**
     * 方法出现异常时通知，被反射调用
     *
     * @param method
     * @param args
     * @param target
     * @param ex
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

        // 当此处出现异常时，会覆盖目标方法的异常
//        int a = 1 / 0;

        String methodName = method.getDeclaringClass().getName() + "#" + method.getName();
        System.out.println(PRE + methodName + " throwing exception["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS").format(new Date()) + "]");
    }
}
