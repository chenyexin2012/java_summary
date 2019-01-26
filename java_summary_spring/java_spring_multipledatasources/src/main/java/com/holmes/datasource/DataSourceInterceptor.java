package com.holmes.datasource;

import com.holmes.enums.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
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

}
