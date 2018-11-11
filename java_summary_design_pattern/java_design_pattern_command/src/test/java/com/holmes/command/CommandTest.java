package com.holmes.command;

import org.junit.Test;

public class CommandTest {

    @Test
    public void test() {

        // 常创建一个命令接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();

        // 创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command speedCommand = new SpeedCommand(audioPlayer);

        // 创建请求者对象
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setStopCommand(stopCommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setSpeedCommand(speedCommand);

        // 调用命令
        keypad.play();
        keypad.stop();
        keypad.rewind();
        keypad.speed();
    }
}
