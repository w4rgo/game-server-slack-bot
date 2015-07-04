package com.zombiespain.zsbot.slack.commands;

public interface ICommand {
    String name();

    String description();

    int paramNumber();

    void execute(String[] args);

    void doTask(String[] args);
}
