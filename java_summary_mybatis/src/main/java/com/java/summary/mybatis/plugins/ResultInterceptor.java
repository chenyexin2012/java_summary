package com.java.summary.mybatis.plugins;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * 查询结果过滤
 *
 * @author Administrator
 */
@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}))
public class ResultInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

//        Object target = invocation.getTarget();
//        ResultSetHandler resultSetHandler = (ResultSetHandler) target;

        // 获取执行结果
        Object result = invocation.proceed();
        if (result instanceof List) {
            List list = (List) result;
            System.out.println(list);
            System.out.println("对结果进行过滤");
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
