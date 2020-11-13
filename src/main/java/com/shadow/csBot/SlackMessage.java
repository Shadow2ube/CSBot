package com.shadow.csBot;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

public class SlackMessage {
    public static void send(String message) {
        Slack slack = Slack.getInstance();


        Payload payload = Payload.builder().text(message).build();

        WebhookResponse response = null;

        try {
            response = slack.send(CSBot.webhook, payload);

        } catch (IOException e) {
            send("Someone tried to send you a message and it failed");
            e.printStackTrace();
        }

        System.out.println(response);
    }
}
