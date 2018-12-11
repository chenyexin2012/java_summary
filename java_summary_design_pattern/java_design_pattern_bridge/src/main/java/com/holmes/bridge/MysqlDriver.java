package com.holmes.bridge;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/11 19:28
 * @Version: 1.0.0
 */
public class MysqlDriver implements Driver {

    @Override
    public void connect() {
        System.out.println("mysql connect...");
    }
}
