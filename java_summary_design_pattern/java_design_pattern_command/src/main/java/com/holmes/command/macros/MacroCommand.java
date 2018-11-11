package com.holmes.command.macros;

import com.holmes.command.Command;

/**
 * 宏命令接口
 */
public interface MacroCommand extends Command {

    /**
     * 添加一个命令
     *
     * @param command
     */
    public void addCommand(Command command);

    /**
     * 移除一个命令
     *
     * @param command
     */
    public void removeCommand(Command command);
}
