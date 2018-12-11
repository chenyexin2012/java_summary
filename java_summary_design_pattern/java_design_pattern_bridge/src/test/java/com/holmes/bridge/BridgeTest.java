package com.holmes.bridge;

public class BridgeTest {

    public static void main(String[] args) {

        Driver mysqlDriver = new MysqlDriver();
        Driver oracleDriver = new OracleDriver();

        JavaBridge bridge = new JavaBridge(mysqlDriver);
        bridge.connect();

        bridge.setDriver(oracleDriver);
        bridge.connect();
    }
}
