package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.servers.GameServersPersistanceService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;

public class RemoveServerCommand extends BaseCommand {

    public String name() {
        return "removeserver";
    }

    public String description() {
        return "removeserver <name> removes a server";
    }

    public int paramNumber() {
        return 1;
    }

    public void doTask(String[] args) {
        try {
            GameServersPersistanceService.getInstance().removeServer(args[0]);

            SlackService.getInstance().sendMessage("Removing: " + args[0]);
        } catch (NumberFormatException e) {
            SlackService.getInstance().sendMessage("The port must be a number");
        }




    }
}
