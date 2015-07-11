package com.zombiespain.zsbot.gameserver;

import com.github.koraktor.steamcondenser.exceptions.PacketFormatException;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;


import com.github.koraktor.steamcondenser.steam.SteamPlayer;
import com.github.koraktor.steamcondenser.steam.servers.GoldSrcServer;
import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import com.zombiespain.zsbot.servers.GameServer;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class SteamServerService implements ISteamServerService {

    private HashMap<String, SourceServer> gameServerConnections = new HashMap<String, SourceServer>();

    private static SteamServerService instance;

    public static SteamServerService getInstance() {
        if (instance == null) {
            instance = new SteamServerService();
        }
        return instance;
    }


    public void addServer(GameServer gameServer) {
        try {
            String ip = gameServer.getIp();
            Integer port = Integer.valueOf(gameServer.getPort());

            System.out.println("Creating : " + ip + " " + port);

            gameServerConnections.put(gameServer.getName(), new SourceServer(ip, port));
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        }
    }


    public void connectServer(String gameServer) throws GameServerNotFoundException {
        try {
            if (gameServerConnections.containsKey(gameServer)) {
                gameServerConnections.get(gameServer).initialize();

            } else {
                throw new GameServerNotFoundException();
            }
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public boolean connectRcon(String gameServer, String pass) throws GameServerNotFoundException {
        try {
            if (gameServerConnections.containsKey(gameServer)) {
                return gameServerConnections.get(gameServer).rconAuth(pass);

            } else {
                throw new GameServerNotFoundException();
            }
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String rconExec(String gameServer, String command) throws GameServerNotFoundException , PacketFormatException {
        String result ="";

        try {
            if (gameServerConnections.containsKey(gameServer)) {
                System.out.println("Rcon command: " + command);
                result = gameServerConnections.get(gameServer).rconExec(command);
                return result;

            } else {
                throw new GameServerNotFoundException();
            }
        } catch (PacketFormatException e) {
            System.out.println(e.getMessage());
            return result;
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return ":( :( :(";
    }


    public HashMap<String, Object> getServerInfo(String name) throws GameServerNotFoundException {
        try {
            if (gameServerConnections.containsKey(name)) {
                gameServerConnections.get(name).updateServerInfo();
                return gameServerConnections.get(name).getServerInfo();
            } else {
                throw new GameServerNotFoundException();
            }
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, SteamPlayer> getPlayers(String name) throws GameServerNotFoundException {
        try {
            if (gameServerConnections.containsKey(name)) {
                gameServerConnections.get(name).updatePlayers();
                return gameServerConnections.get(name).getPlayers();
            } else {
                throw new GameServerNotFoundException();
            }
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPing(String name) throws GameServerNotFoundException {
        try {
            if (gameServerConnections.containsKey(name)) {
                gameServerConnections.get(name).updatePing();
                return gameServerConnections.get(name).getPing();
            } else {
                throw new GameServerNotFoundException();
            }
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void disconnect(String gameServer) throws GameServerNotFoundException {
        if (gameServerConnections.containsKey(gameServer)) {
            gameServerConnections.get(gameServer).disconnect();
        } else {
            throw new GameServerNotFoundException();
        }

    }


    public Set<String> getServers() {
        return gameServerConnections.keySet();
    }
}
