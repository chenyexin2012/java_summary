package com.holmes.pojo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class BasePo implements Serializable {

    /**
     * 数据源标识
     */
    private String dataSourceFlag;

    public BasePo() {
    }

    public BasePo(String dataSourceFlag) {
        this.dataSourceFlag = dataSourceFlag;
    }

    public String getDataSourceFlag() {
        return dataSourceFlag;
    }

    public void setDataSourceFlag(String dataSourceFlag) {
        this.dataSourceFlag = dataSourceFlag;
    }
}
