package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

public class GameServerConnectAllCommand extends BaseCommand {

    @Override
    public String name() {
        return "connectall";
    }

    @Override
    public String description() {
        return "connectall - connects to all gameservers";
    }

    @Override
    public int paramNumber() {
        return 0;
    }

    @Override
    public void doTask(String[] args) {

        try {
            for (String gameServer : SteamServerService.getInstance().getServers()) {
                SteamServerService.getInstance().connectServer(gameServer);
                SlackService.getInstance().sendMessage("Connected to : " + gameServer);
            }
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }


    }
}
