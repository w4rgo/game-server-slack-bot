package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

public class GameServerDisconnectAllCommand extends BaseCommand {

    @Override
    public String name() {
        return "disconnectall";
    }

    @Override
    public String description() {
        return "disconnectall - disconnects from all gameservers";
    }

    @Override
    public int paramNumber() {
        return 0;
    }

    @Override
    public void doTask(String[] args) {

        try {
            for (String gameServer : SteamServerService.getInstance().getServers()) {
                SteamServerService.getInstance().disconnect(args[0]);
                SlackService.getInstance().sendMessage("Disconnected from : " + gameServer);
            }
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }


    }
}
