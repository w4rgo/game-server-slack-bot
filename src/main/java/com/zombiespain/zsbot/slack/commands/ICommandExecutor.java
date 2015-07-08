package com.zombiespain.zsbot.slack.commands;

public interface ICommandExecutor {

    void addCommand(ICommand command);
    void execute(String command) throws CommandNotFoundException;

}
