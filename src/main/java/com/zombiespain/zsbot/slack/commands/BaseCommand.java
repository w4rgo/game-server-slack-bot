package com.zombiespain.zsbot.slack.commands;

import com.zombiespain.zsbot.slack.SlackService;

public abstract class BaseCommand implements ICommand{
    public void execute(String[] args) {
        if(args.length < paramNumber()) {
            SlackService.getInstance().sendMessage(description());
            return;
        }
        doTask(args);
    }
}
