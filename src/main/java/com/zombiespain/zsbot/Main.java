package com.zombiespain.zsbot;

import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.zombiespain.zsbot.config.ConfigurationService;
import com.zombiespain.zsbot.db.JongoService;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import com.zombiespain.zsbot.servers.GameServer;
import com.zombiespain.zsbot.servers.GameServersPersistanceService;
import com.zombiespain.zsbot.slack.SlackService;
import com.zombiespain.zsbot.slack.commands.*;
import com.zombiespain.zsbot.slack.commands.server.*;
import com.zombiespain.zsbot.slack.listeners.SlackCommandListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Main {


    public static void main(String[] args) throws IOException, TimeoutException, SteamCondenserException, CommandNotFoundException {

        ConfigurationService.getInstance().init();

        JongoService.getInstance().init();

        CommandExecutor.getInstance().addCommand(new GameServerInfoCommand());
        CommandExecutor.getInstance().addCommand(new GameServerPlayersCommand());
        CommandExecutor.getInstance().addCommand(new HelpCommand());
        CommandExecutor.getInstance().addCommand(new GameServerPingCommand());
        CommandExecutor.getInstance().addCommand(new ListServersCommand());
        CommandExecutor.getInstance().addCommand(new AddServerCommand());
        CommandExecutor.getInstance().addCommand(new GameServerConnectCommand());
        CommandExecutor.getInstance().addCommand(new GameServerDisconnectAllCommand());
        CommandExecutor.getInstance().addCommand(new GameServerConnectAllCommand());
        //CommandExecutor.getInstance().addCommand(new GameServerDisconnectCommand());


        SlackService.getInstance().connect();
        SlackService.getInstance().getSession().addMessagePostedListener(new SlackCommandListener());

        populateGameServers();

        startCommandLineClient();
    }

    private static void populateGameServers() {
        HashMap<String, GameServer> servers = GameServersPersistanceService.getInstance().getServers();
        for (GameServer server : servers.values()) {
            SteamServerService.getInstance().addServer(server);
            System.out.println("ZSLACKBOT> Found gameserver: " + server.toString() );
        }
    }

    private static void startCommandLineClient() throws CommandNotFoundException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();
            System.out.println(command);
            CommandExecutor.getInstance().execute(command);
        }
    }


    private static void runOSCommand(String command, SlackSession session) throws IOException {
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command ls
        java.lang.Process p = rt.exec(command);

        java.io.InputStream is = p.getInputStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
        // And print each line
        String s = null;
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
            session.sendMessageOverWebSocket(session.findChannelByName("zsinternal"), s, null);
        }
        is.close();
    }

}
