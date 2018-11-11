package com.holmes.command;

/**
 *
 * 一个具体的命令角色
 */
public class PlayCommand implements Command {

    private AudioPlayer audioPlayer;

    public PlayCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.play();
    }
}
