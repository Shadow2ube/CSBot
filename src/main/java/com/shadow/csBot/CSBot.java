package com.shadow.csBot;

import com.shadow.csBot.listeners.Listener;
import com.shadow.csBot.music.MusicListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.shadow.csBot.SlackMessage.SendSlackMessage;

public class CSBot {

    private static final String token = "NzcxMDc2ODI1MDk4NjE2ODky.X5m3QQ.rYzOFubhA7QYW1ikNOELSpQDqSA";
    public static final String webhook = "https://hooks.slack.com/services/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";

    public static void main(String[] args) throws InterruptedException {
        String hostname = "Unknown";
        String localIp = "Unknown";
        String ip = "Unknown";
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
            localIp = addr.getHostAddress();

            URL whatsMyIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatsMyIp.openStream()));
            ip = in.readLine(); //you get the IP as a String
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            try {
                JDABuilder.createDefault(token)
                        .addEventListeners(new Listener())
//                        .addEventListeners(new GeneralListener())
                        .addEventListeners(new MusicListener())
                        .setActivity(Activity.watching("you"))
                        .build();

                SendSlackMessage("CSBot instance started:\non: "
                        + hostname
                        + "\nat: "
                        + localIp
                        + " : "
                        + ip
                        + "\non: "
                        + dtf.format(now));

            } catch (LoginException e) {
                SendSlackMessage("Login to CSBot failed");
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            SendSlackMessage("Hostname can not be resolved");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String finalHostname = hostname;
        String finalLocalIp = localIp;
        String finalIp = ip;

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                SendSlackMessage("CSBot instance stopped:\non: "
                        + finalHostname
                        + "\nat: "
                        + finalLocalIp
                        + "\nat: "
                        + finalIp
                        + "\non: "
                        + dtf.format(now));
            }
        }));
    }
}