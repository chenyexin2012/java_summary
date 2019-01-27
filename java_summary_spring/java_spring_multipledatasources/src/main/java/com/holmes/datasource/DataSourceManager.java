package com.holmes.datasource;

import com.holmes.enums.DataSourceType;

/**
 * @author Administrator
 */
public class DataSourceManager {

    private static ThreadLocal<DataSourceType> dataSourceThreadLocal = new ThreadLocal<DataSourceType>(){
        @Override
        protected DataSourceType initialValue() {
            return DataSourceType.DATA_SOURCE_A;
        }
    };

    public static DataSourceType get(){
        return dataSourceThreadLocal.get();
    }

    public static void set(DataSourceType dataSourceType){
        dataSourceThreadLocal.set(dataSourceType);
    }

    public static void clear() {
        if(null != dataSourceThreadLocal.get()) {
            dataSourceThreadLocal.remove();
        }
    }
    public static void reset(){
        dataSourceThreadLocal.set(DataSourceType.DATA_SOURCE_A);
    }
}
