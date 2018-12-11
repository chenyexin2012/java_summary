package com.holmes.bridge;

/**
 * @Description: 桥接模式
 * @Author: holmes
 * @CreateDate: 2018/12/11 19:36
 * @Version: 1.0.0
 */
public abstract class Bridge {

    private Driver driver;

    public Bridge(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    protected void connect() {
        driver.connect();
    }
}
