package com.holmes.command;

/**
 * 一个具体的命令角色
 */
public class SpeedCommand implements Command {

    private AudioPlayer audioPlayer;

    public SpeedCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.speed();
    }
}
