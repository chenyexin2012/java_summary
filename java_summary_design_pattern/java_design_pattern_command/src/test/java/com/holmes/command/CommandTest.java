package com.holmes.command;

import com.holmes.command.macros.MacroAudioCommand;
import com.holmes.command.macros.MacroCommand;
import org.junit.Test;

public class CommandTest {

    @Test
    public void commandTest() {

        // 创建一个命令接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();

        // 创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command speedCommand = new SpeedCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);

        // 创建请求者对象
        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setStopCommand(stopCommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setSpeedCommand(speedCommand);

        // 执行命令
        keypad.play();
        keypad.rewind();
        keypad.speed();
        keypad.stop();
    }

    @Test
    public void macroCommandTest() {

        // 创建一个命令接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();

        // 创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command speedCommand = new SpeedCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);

        // 宏命令对象
        MacroCommand macroCommand = new MacroAudioCommand();
        macroCommand.addCommand(playCommand);
        macroCommand.addCommand(rewindCommand);
        macroCommand.addCommand(speedCommand);
        macroCommand.addCommand(stopCommand);

        // 执行宏命令
        macroCommand.execute();
    }
}
