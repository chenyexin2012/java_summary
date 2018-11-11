package com.holmes.command;

/**
 * 命令的接收者
 * 模拟录音机的播放、停止、倒带、快进
 */
public class AudioPlayer {

    public void play() {
        System.out.println("play...");
    }

    public void stop() {
        System.out.println("stop...");
    }

    public void rewind() {
        System.out.println("rewind...");
    }

    public void speed() {
        System.out.println("speed...");
    }
}
