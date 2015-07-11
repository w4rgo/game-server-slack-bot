package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.CommandExecutor;
import com.zombiespain.zsbot.slack.commands.ICommand;

import java.util.HashMap;

public class HelpCommand extends BaseCommand {

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "shows available commands";
    }

    @Override
    public int paramNumber() {
        return 0;
    }

    @Override
    public void doTask(String[] args) {

        HashMap<String, ICommand> commands = CommandExecutor.getInstance().getCommands();
        for (ICommand command : commands.values()) {
            SlackService.getInstance().sendMessage(command.name() + " : " + command.description());
        }
    }
}
