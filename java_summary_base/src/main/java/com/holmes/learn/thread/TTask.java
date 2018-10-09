package com.holmes.learn.thread;

import java.util.concurrent.Callable;

public class TTask implements Callable<TaskInfo> {

    private String name;
    private long time;

    public TTask(String name, long time) {
        super();
        this.name = name;
        this.time = time;
    }

    @Override
    public TaskInfo call() throws Exception {
        System.out.println(this.name + " is Running...");
        Thread.sleep(time);
        System.out.println(this.name + " is finished...");
        return new TaskInfo(name);
    }
}
