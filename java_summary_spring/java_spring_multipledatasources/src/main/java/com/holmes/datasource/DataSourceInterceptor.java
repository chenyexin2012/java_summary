package com.holmes.datasource;

import com.holmes.enums.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Aspect
@Order(0)
@Component
public class DataSourceInterceptor {

    @Before(value = "execution(* com.holmes.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + DataSourceManager.get());
//        DataSourceManager.set(DataSourceType.DATA_SOURCE_A);
    }

    @Before(value = "execution(* com.holmes.service.*.insert(..))")
    public void beforeInsert(JoinPoint joinPoint) {

        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            System.out.println(object);
        }

        DataSourceManager.set(DataSourceType.DATA_SOURCE_B);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + DataSourceManager.get());
    }

    @AfterReturning(value = "execution(* com.holmes.service.*.*(..))")
    public void afterReturning() {

    }
}
