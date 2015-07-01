package com.zombiespain.zsbot.slack.commands.server;

import com.github.koraktor.steamcondenser.steam.SteamPlayer;
import com.zombiespain.zsbot.gameserver.GameServerNotFoundException;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;
import com.zombiespain.zsbot.slack.commands.ZSError;

import java.util.HashMap;

public class GameServerPlayersCommand extends BaseCommand {

    public String name() {
        return "players";
    }

    public String description() {
        return "players <servername> - shows players of a server";
    }

    public int paramNumber() {
        return 1;
    }

    public void doTask(String[] args) {
        HashMap<String, SteamPlayer> serverPlayers = null;
        try {
            serverPlayers = SteamServerService.getInstance().getPlayers(args[0]);
        } catch (GameServerNotFoundException e) {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }
        if (serverPlayers != null) {
            SlackService.getInstance().sendMessage("Number of players: " + String.valueOf(serverPlayers.size()));
            SlackService.getInstance().sendMessage( serverPlayers.toString());
        } else {
            SlackService.getInstance().sendMessage(ZSError.TIMEOUT);
        }

    }
}
