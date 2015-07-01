package com.zombiespain.zsbot.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.zombiespain.zsbot.config.ConfigurationService;
import org.jongo.Jongo;

public class JongoService implements IJongoService {
    private static JongoService instance;
    private Jongo jongo;

    public static JongoService getInstance() {
        if (instance == null) {
            instance = new JongoService();
        }
        return instance;
    }

    public void init() {
        DB db = new MongoClient(ConfigurationService.getInstance().getMongodbIp(),ConfigurationService.getInstance().getMongodbPort()).getDB(ConfigurationService.getInstance().getMongodbDatabase());
        jongo = new Jongo(db);
    }

    public Jongo getJongo() {
        return jongo;
    }
}

