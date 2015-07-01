package com.zombiespain.zsbot.slack.commands;

import com.zombiespain.zsbot.slack.SlackService;

public abstract class BaseCommand implements ICommand{
    @Override
    public void execute(String[] args) {
        if(paramNumber() != args.length) {
            SlackService.getInstance().sendMessage(description());
            return;
        }
        doTask(args);
    }
}
