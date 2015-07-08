package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.servers.GameServer;
import com.zombiespain.zsbot.servers.GameServersPersistanceService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;

import java.util.HashMap;

public class ListServersCommand extends BaseCommand {

    public String name() {
        return "listservers";
    }

    public String description() {
        return "lists saved servers";
    }

    public int paramNumber() {
        return 0;
    }

    public void doTask(String[] args) {

        HashMap<String, GameServer> servers = GameServersPersistanceService.getInstance().getServers();

        for (GameServer server : servers.values()) {
            SlackService.getInstance().sendMessage( server.toString());
        }
    }
}
