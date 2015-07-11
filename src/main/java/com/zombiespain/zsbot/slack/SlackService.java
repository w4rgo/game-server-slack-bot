package com.zombiespain.zsbot.slack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.zombiespain.zsbot.config.ConfigurationService;

import java.io.IOException;

public class SlackService implements ISlackService {
    private static SlackService instance;
    private SlackSession session;

    public static SlackService getInstance() {
        if (instance == null) {
            instance = new SlackService();
        }
        return instance;
    }

    public SlackService() {
        try {
            InitSlack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SlackSession InitSlack() throws IOException {
        session = SlackSessionFactory.createWebSocketSlackSession(ConfigurationService.getInstance().getSlackToken());
        return session;
    }

    public SlackSession getSession() {
        return session;
    }

    public void connect() {
        try {
            session.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String channel, String message) {
        //session.sendMessageOverWebSocket(session.findChannelByName(channel), message, null);
        System.out.println(message);
    }

    public void sendMessage(String message) {
        //session.sendMessageOverWebSocket(session.findChannelByName(ConfigurationService.getInstance().getSlackChannel()), message, null);
        System.out.println(message);
    }

}

