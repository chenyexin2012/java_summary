package com.holmes.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Aspect
@Order(0)
@Component
public class TransactionalInterceptor {

//    @Pointcut(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
//    public void method() {
//    }
//
//    @Before("method()")
//    public void before(JoinPoint point) {
//
//        String method = point.getSignature().getName();
//
//        System.out.println("Transactional>>>>>>>>>>>" + method + ">>>>>>>>start");
//        System.out.println(DataSourceManager.get());
//    }
//
//    @AfterReturning("method()")
//    public void afterReturning(JoinPoint point) {
//
//        DataSourceManager.clear();
//        String method = point.getSignature().getName();
//        System.out.println("Transactional>>>>>>>>>>>" + method + ">>>>>>>>end");
//    }
}
