package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

import java.util.HashMap;

public class GameServerInfoCommand extends BaseCommand {

    @Override
    public String name() {
        return "info";
    }

    @Override
    public String description() {
        return "info <servername> - shows info of a server";
    }

    @Override
    public int paramNumber() {
        return 1;
    }

    @Override
    public void doTask(String[] args) {

        HashMap<String, Object> serverInfo = null;
        try {
            serverInfo = SteamServerService.getInstance().getServerInfo(args[0]);
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }
        if (serverInfo != null) {
            SlackService.getInstance().sendMessage(serverInfo.toString());
        } else {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }

    }
}
