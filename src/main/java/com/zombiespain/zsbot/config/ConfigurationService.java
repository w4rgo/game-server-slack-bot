package com.zombiespain.zsbot.config;

import java.io.*;
import java.util.Properties;

public class ConfigurationService {
    private static ConfigurationService instance;
    Properties prop;
    private String mongodbIp;
    private String mongodbPort;
    private String mongodbDatabase;
    private String slackToken;
    private String slackChannel;
    private String botName;

    public static ConfigurationService getInstance() {
        if (instance == null) {
            instance = new ConfigurationService();
        }
        return instance;
    }



    public void init() {
        prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            mongodbIp = prop.getProperty("mongodb-ip");
            mongodbPort = prop.getProperty("mongodb-port");
            slackToken = prop.getProperty("slack-token");
            slackChannel = prop.getProperty("slack-channel");
            mongodbDatabase = prop.getProperty("mongodb-database");
            botName = prop.getProperty("slack-bot-name");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getMongodbIp() {
        return mongodbIp;
    }

    public int getMongodbPort() {
        return Integer.valueOf(mongodbPort);
    }

    public String getSlackToken() {
        return slackToken;
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public String getMongodbDatabase() {
        return mongodbDatabase;
    }

    public String getBotName() {
        return botName;
    }
}

