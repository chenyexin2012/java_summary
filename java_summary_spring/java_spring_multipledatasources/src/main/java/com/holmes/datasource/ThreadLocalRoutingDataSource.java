package com.holmes.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 处理多数据源
 *
 * @author Administrator
 */
public class ThreadLocalRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceManager.get();
    }
}
