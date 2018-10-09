package com.holmes.learn.thread;

public class MyTask extends Task<TaskInfo> {

    private TaskInfo taskInfo;

    public MyTask(String name) {
        this.taskInfo = new TaskInfo();
        this.taskInfo.setTaskName(name);
    }

    @Override
    public TaskInfo runTask() {

        System.out.println("正在执任务：" + this.taskInfo.getTaskName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("任务：" + this.taskInfo.getTaskName() + "执行完毕");

        taskInfo.setFinished(true);

        return taskInfo;
    }
}
