package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

public class GameServerRconConnectCommand extends BaseCommand {
    public String name() {
        return "rcon";
    }

    public String description() {
        return "rcon <server> <pass>";
    }

    public int paramNumber() {
        return 2;
    }

    public void doTask(String[] args) {
        try {
            if(SteamServerService.getInstance().connectRcon(args[0],args[1])) {
                SlackService.getInstance().sendMessage("Connected!");

            } else {
                SlackService.getInstance().sendMessage(":thumbsdown: :( :(");

            }
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }

    }
}
