package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

public class GameServerConnectCommand extends BaseCommand {

    @Override
    public String name() {
        return "connect";
    }

    @Override
    public String description() {
        return "connect <servername> - connects to a gameserver";
    }

    @Override
    public int paramNumber() {
        return 1;
    }

    @Override
    public void doTask(String[] args) {

        try {
            SteamServerService.getInstance().connectServer(args[0]);
            SlackService.getInstance().sendMessage("Connected!");
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }


    }
}
