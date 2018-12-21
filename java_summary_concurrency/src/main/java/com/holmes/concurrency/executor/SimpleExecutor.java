package com.holmes.concurrency.executor;

import java.util.concurrent.Executor;

/**
 * @Description: Executor接口的简单实现，为每个任务启动一个线程
 * @Author: holmes
 * @CreateDate: 2018/12/21 11:03
 * @Version: 1.0.0
*/
public class SimpleExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
