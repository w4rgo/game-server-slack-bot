package com.zombiespain.zsbot.gameserver;

import com.github.koraktor.steamcondenser.exceptions.PacketFormatException;
import com.github.koraktor.steamcondenser.steam.SteamPlayer;
import com.zombiespain.zsbot.servers.GameServer;

import java.util.HashMap;

public interface ISteamServerService {

    void addServer(GameServer gameServer);

    void connectServer(String gameServer) throws GameServerNotFoundException;

    boolean connectRcon(String gameServer, String pass) throws GameServerNotFoundException;

    String rconExec(String gameServer, String command) throws GameServerNotFoundException, PacketFormatException;

    HashMap<String, Object> getServerInfo(String name) throws GameServerNotFoundException;

    HashMap<String, SteamPlayer> getPlayers(String name) throws GameServerNotFoundException;

    int getPing(String name) throws GameServerNotFoundException;

    void disconnect(String gameServer) throws GameServerNotFoundException;

}
