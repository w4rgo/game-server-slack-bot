package com.zombiespain.zsbot.slack.commands.server;

import com.zombiespain.zsbot.gameserver.RconService;
import com.zombiespain.zsbot.servers.GameServer;
import com.zombiespain.zsbot.servers.GameServersPersistanceService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.BaseCommand;

public class GameServerRconExecCommand extends BaseCommand {
    public String name() {
        return "rconexec";
    }

    public String description() {
        return "rconexec <server> <command>";
    }

    public int paramNumber() {
        return 2;
    }

    public void doTask(String[] args) {

        GameServer server = GameServersPersistanceService.getInstance().getServer(args[0]);

        RconService.getInstance().rconExec(server, args[1]);


    }
}
