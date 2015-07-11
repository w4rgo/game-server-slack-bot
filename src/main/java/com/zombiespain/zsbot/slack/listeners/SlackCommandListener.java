package com.zombiespain.zsbot.slack.listeners;

import com.ullink.slack.simpleslackapi.SlackAttachment;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import com.zombiespain.zsbot.config.ConfigurationService;
import com.zombiespain.zsbot.slack.commands.CommandExecutor;
import com.zombiespain.zsbot.slack.commands.CommandNotFoundException;

public class SlackCommandListener implements SlackMessagePostedListener {


    public void onEvent(SlackMessagePosted event, SlackSession session) {
        System.out.println("mes:" + event.getMessageContent() + " in " + event.getChannel().getName());

        String message = event.getMessageContent();
        boolean correctChannel = event.getChannel().getName().equals(ConfigurationService.getInstance().getSlackChannel());
        boolean notTheBot = !event.getSender().getUserMail().equals(ConfigurationService.getInstance().getBotName());

        if (correctChannel && notTheBot) {

            try {
                CommandExecutor.getInstance().execute(message);
            } catch (CommandNotFoundException e) {
                sendMessageToChannel(session, ConfigurationService.getInstance().getSlackChannel(), "Command not found");
            }
        }
    }

    private void sendMessageToChannel(SlackSession session, String channel, String message) {

        SlackAttachment slackAttachment = new SlackAttachment("players", "fallback", "message", "pretext");
        session.sendMessageOverWebSocket(session.findChannelByName(channel), message, slackAttachment);
    }
}
