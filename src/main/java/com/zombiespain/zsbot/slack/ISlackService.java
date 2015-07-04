package com.zombiespain.zsbot.slack;

import com.ullink.slack.simpleslackapi.SlackSession;

public interface ISlackService {
    SlackSession getSession();

    void connect();

    void sendMessage(String channel, String message);

    void sendMessage(String message);
}
