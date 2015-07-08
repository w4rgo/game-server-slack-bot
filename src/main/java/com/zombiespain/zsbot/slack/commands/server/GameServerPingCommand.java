package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

public class GameServerPingCommand extends BaseCommand {

    @Override
    public String name() {
        return "ping";
    }

    @Override
    public String description() {
        return "ping <servername> - Pings a gameserver";
    }

    @Override
    public int paramNumber() {
        return 1;
    }

    @Override
    public void doTask(String[] args) {

        int ping = 0;
        try {
            ping = SteamServerService.getInstance().getPing(args[0]);
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }

        if (ping != -1) {
            SlackService.getInstance().sendMessage("Ping: " + ping);
        } else {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }

    }
}
