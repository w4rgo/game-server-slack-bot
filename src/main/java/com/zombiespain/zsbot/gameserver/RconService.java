package com.zombiespain.zsbot.gameserver;

import com.zombiespain.zsbot.servers.GameServer;
import com.zombiespain.zsbot.slack.SlackService;

import java.io.IOException;
import java.io.InputStreamReader;

public class RconService {
    private static RconService rconService;

    public static RconService getInstance() {
        if (rconService == null) {
            rconService = new RconService();
        }
        return rconService;
    }


    public String rconExec(String server, String port, String pass, String command) {
        return null;
    }

    public void rconExec(GameServer server, String arg) {

        try {

            String command = "./rcon -P" + server.getRconPass() + " -a" + server.getIp() + " -p" + server.getRconPort() + " " + arg;
            System.out.println(command);
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            // Start a new process: UNIX command ls
            Process p = null;
            p = rt.exec(command);

            java.io.InputStream is = p.getInputStream();
            java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
            // And print each line
            String s = null;
            while ((s = reader.readLine()) != null) {
                SlackService.getInstance().sendMessage(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
