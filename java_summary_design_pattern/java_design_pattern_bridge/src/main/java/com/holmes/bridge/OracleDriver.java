package com.holmes.bridge;

/**
 * @Description:
 * @Author: holmes
 * @CreateDate: 2018/12/11 19:29
 * @Version: 1.0.0
 */
public class OracleDriver implements Driver {

    @Override
    public void connect() {
        System.out.println("oracle connect...");
    }
}
