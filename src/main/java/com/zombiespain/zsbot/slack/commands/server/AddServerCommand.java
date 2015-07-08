package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.servers.GameServer;
import com.zombiespain.zsbot.servers.GameServersPersistanceService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;

public class AddServerCommand extends BaseCommand {

    public String name() {
        return "addserver";
    }

    public String description() {
        return "addserver <name> <ip> <port> - adds a server";
    }

    public int paramNumber() {
        return 3;
    }

    public void doTask(String[] args) {
        try {
            GameServer server = new GameServer(args[0], args[1], Integer.valueOf(args[2]));
            GameServersPersistanceService.getInstance().addServer(server);

            SlackService.getInstance().sendMessage("Added: " + server.getName());
        } catch (NumberFormatException e) {
            SlackService.getInstance().sendMessage("The port must be a number");
        }




    }
}
