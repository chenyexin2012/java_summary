package com.holmes.concurrency.concurrent.atomic;

/**
 * @Description: java类作用描述
 * @Author: holmes
 * @Version: 1.0.0
 */
public class Data {

    public volatile int publicVar = 0;

    protected volatile int protectedVar = 0;

    private volatile int privateVar = 0;

    public volatile static int staticVar = 0;

    public final int finalVar = 0;

    public volatile Integer integerVar = 0;

    public volatile Long longVar = 0L;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Data{");
        sb.append("publicVar=").append(publicVar);
        sb.append(", protectedVar=").append(protectedVar);
        sb.append(", privateVar=").append(privateVar);
        sb.append(", staticVar=").append(staticVar);
        sb.append(", finalVar=").append(finalVar);
        sb.append(", integerVar=").append(integerVar);
        sb.append(", longVar=").append(longVar);
        sb.append('}');
        return sb.toString();
    }
}