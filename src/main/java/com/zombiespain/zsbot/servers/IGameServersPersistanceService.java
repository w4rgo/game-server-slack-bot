package com.zombiespain.zsbot.servers;

public interface IGameServersPersistanceService {
    void addServer(GameServer server);
    void removeServer(GameServer server);
    GameServer getServer(String ip);
}
