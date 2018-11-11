package com.holmes.command;

/**
 * 命令的请求者
 * 录音机的键盘
 */
public class Keypad {

    private Command playCommand;
    private Command stopCommand;
    private Command rewindCommand;
    private Command speedCommand;

    public void setPlayCommand(Command playCommand) {
        this.playCommand = playCommand;
    }

    public void setStopCommand(Command stopCommand) {
        this.stopCommand = stopCommand;
    }

    public void setRewindCommand(Command rewindCommand) {
        this.rewindCommand = rewindCommand;
    }

    public void setSpeedCommand(Command speedCommand) {
        this.speedCommand = speedCommand;
    }

    public void play() {
        playCommand.execute();
    }

    public void stop() {
        stopCommand.execute();
    }

    public void rewind() {
        rewindCommand.execute();
    }

    public void speed() {
        speedCommand.execute();
    }
}
