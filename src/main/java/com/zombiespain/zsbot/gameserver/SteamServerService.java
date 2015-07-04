package com.zombiespain.zsbot.gameserver;

import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.SteamPlayer;
import com.github.koraktor.steamcondenser.steam.servers.GoldSrcServer;
import com.zombiespain.zsbot.servers.GameServer;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class SteamServerService implements ISteamServerService {

    private HashMap<String, GoldSrcServer> gameServerConnections = new HashMap<String, GoldSrcServer>();

    private static SteamServerService instance;

    public static SteamServerService getInstance() {
        if (instance == null) {
            instance = new SteamServerService();
        }
        return instance;
    }


    @Override
    public void addServer(GameServer gameServer) {
        try {
            String ip = gameServer.getIp();
            Integer port = Integer.valueOf(gameServer.getPort());

            System.out.println("Creating GoldSrcServer: " + ip + " " + port);

            gameServerConnections.put(gameServer.getName(), new GoldSrcServer(ip, port));
        } catch (SteamCondenserException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
