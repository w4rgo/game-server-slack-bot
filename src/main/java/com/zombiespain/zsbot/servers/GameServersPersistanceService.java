package com.zombiespain.zsbot.servers;

import com.zombiespain.zsbot.db.JongoService;
import com.zombiespain.zsbot.gameserver.SteamServerService;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.HashMap;

public class GameServersPersistanceService implements IGameServersPersistanceService {
    public static final String GAME_SERVERS_COLLECTION = "servers";
    private static GameServersPersistanceService instance;


    public static GameServersPersistanceService getInstance() {
        if (instance == null) {
            instance = new GameServersPersistanceService();
        }
        return instance;
    }

    public void addServer(GameServer server) {
        MongoCollection servers = getGameServersCollection();
        servers.insert(server);

        SteamServerService.getInstance().addServer(server);
    }

    public void removeServer(GameServer server) {

        MongoCollection servers = getGameServersCollection();
        servers.remove(server.getName());
    }

    public GameServer getServer(String name) {
        MongoCollection servers = getGameServersCollection();
        MongoCursor<GameServer> server = servers.find(name).as(GameServer.class);
        return server.next();
    }

    public HashMap<String, GameServer> getServers() {

        MongoCollection servers = getGameServersCollection();
        MongoCursor<GameServer> allServers = servers.find().as(GameServer.class);
        HashMap<String, GameServer> retrievedServers = new HashMap<String, GameServer>();

        while (allServers.hasNext()) {
            GameServer gameServer = allServers.next();
            retrievedServers.put(gameServer.getName(), gameServer);
        }

        return retrievedServers;
    }

    private MongoCollection getGameServersCollection() {
        return JongoService.getInstance().getJongo().getCollection(GAME_SERVERS_COLLECTION);
    }
}

