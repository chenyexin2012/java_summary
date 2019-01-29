package com.holmes.datasource;

import com.alibaba.druid.util.StringUtils;
import com.holmes.pojo.BasePo;
import com.holmes.utils.PropertyUtil;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过AOP更换数据源
 *
 * @author Administrator
 */
@Aspect
@Order(0)
@Component
public class DataSourceInterceptor {

    @Before(value = "execution(* com.holmes.dao.*.*(..))")
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 通过@Param注解判断数据源
        Annotation[][] annotationTd = method.getParameterAnnotations();

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof BasePo) {
                    BasePo basePo = (BasePo) args[i];
                    if (!StringUtils.isEmpty(basePo.getDataSourceFlag())) {
                        DataSourceManager.set(basePo.getDataSourceFlag());
                        break;
                    }
                } else if (args[i] instanceof Map) {
                    HashMap map = (HashMap) args[i];
                    if (map.get("dataSourceFlag") != null && !StringUtils.isEmpty(map.get("dataSourceFlag").toString())) {
                        DataSourceManager.set(map.get("dataSourceFlag").toString());
                        break;
                    }
                } else if (args[i] instanceof String) {
                    String arg = (String) args[i];
                    Annotation[] annotations = annotationTd[i];
                    if (annotations.length != 0) {
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof Param) {
                                String param = ((Param) annotation).value();
                                if ("dataSourceFlag".equals(param) && !StringUtils.isEmpty(arg)) {
                                    DataSourceManager.set(arg);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @AfterReturning(value = "execution(* com.holmes.dao.*.*(..))")
    public void afterReturning() {

    }
}
