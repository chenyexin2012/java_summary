package com.holmes.learn.thread;

public class TaskInfo {

    private String taskName;

    private boolean isFinished = false;

    public TaskInfo() {
    }

    public TaskInfo(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public String toString() {
        return "TaskInfo [taskName=" + taskName + ", isFinished=" + isFinished + "]";
    }
}
