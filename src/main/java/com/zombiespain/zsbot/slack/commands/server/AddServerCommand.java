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
        return "addserver <name> <ip> <port> <rconpass> <rconport> - adds a server";
    }

    public int paramNumber() {
        return 5;
    }

    public void doTask(String[] args) {
        try {
            GameServer server = new GameServer(args[0], args[1], Integer.valueOf(args[2]) , args[3] , Integer.valueOf(args[4]));
            GameServersPersistanceService.getInstance().addServer(server);

            SlackService.getInstance().sendMessage("Added: " + server.getName());
        } catch (NumberFormatException e) {
            SlackService.getInstance().sendMessage("The port must be a number");
        }




    }
}
