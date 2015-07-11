package com.zombiespain.zsbot.servers;

public interface IGameServersPersistanceService {
    void addServer(GameServer server);
    void removeServer(String name);
    GameServer getServer(String ip);
}
