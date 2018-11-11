package com.holmes.command.macros;

import com.holmes.command.Command;

import java.util.LinkedList;
import java.util.List;

/**
 * 宏命令接口的具体实现
 */
public class MacroAudioCommand implements MacroCommand {

    private List<Command> commandList = new LinkedList<Command>();


    @Override
    public void addCommand(Command command) {
        commandList.add(command);
    }

    @Override
    public void removeCommand(Command command) {
        commandList.remove(command);
    }

    /**
     * 执行一组命令
     */
    @Override
    public void execute() {
        for (Command command : commandList) {
            command.execute();
        }
    }
}
