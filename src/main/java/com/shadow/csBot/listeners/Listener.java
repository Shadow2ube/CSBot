package com.shadow.csBot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    String prefix = "!";
    boolean isTesting = false;
    public static boolean testIsStarted = false;
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        MessageChannel channel = event.getChannel();
        Message message = event.getMessage();
        Member member = event.getMember();
        Guild guild = event.getGuild();

        if (event.getAuthor().isBot()) return;

        assert member != null;

        //region Hidden commands

        if (args[0].equals("I'm") || args[0].equals("I'M") || args[0].equals("i'm") || args[0].equals("i'M")) {

            StringBuilder _tmp = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("sad") || args[i].equals("gay") || args[i].equals("depressed")
                        || args[i].equals("gae") || args[i].equals("suicidal") || args[i].equals("stupid")
                        || args[i].equals("dumb")) {

                    event.getChannel().sendMessage("LOL\nI know.").queue();
                    event.getChannel().sendMessage("I know.").queue();
                    return;
                }
                else if (i != 0) {
                    _tmp.append(' ');
                    _tmp.append(args[i]);
                }
            }

            event.getChannel().sendMessage("Hi" + _tmp + " I'm Dad!").queue();
        }

        for (String arg : args) {
            if (arg.contains("kerchew") || arg.contains("kachow")) {
                Commands.misc.Kachow(channel);
            }
            else if (arg.contains("tonk") || arg.contains("thunk") || arg.contains("tink")) {
                Commands.misc.Tonk(channel);
            }
        }

        Member[] mentioned = event.getMessage().getMentionedMembers().toArray(new Member[0]);
        for (Member m : mentioned) {
            if (m == event.getGuild().getMemberById("771076825098616892")) {
                event.getChannel().sendMessage("You summoned me?").queue();
            }
        }

        //endregion

        if (args[0].equals(prefix + "ping")) {
            Commands.PingPong(channel);
            print("Command '!ping' used by "+ member.getEffectiveName());

        } else if (args[0].startsWith(prefix + "announce")) {
            Commands.Announce(guild, member, message, channel);
            print("Command '!announce' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "alert")) {
            Commands.Alert(member, channel);
            print("Command '!alert' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "spell")) {
            Commands.Spell(channel);
            print("Command '!spell' used by " + member.getEffectiveName());

        } else if (args[0].startsWith(prefix + "choose")) {
            Commands.Choose(message, channel);
            print("Command '!choose' used by " + member.getEffectiveName());

        } else if (args[0].startsWith(prefix + "d")) {
            Commands.Dice(args, channel);
            print("Command '!d' used by " + member.getEffectiveName());

        }

//        else if (args[0].startsWith(prefix + "idea"))
//            Commands.Idea(event);

        else if (args[0].startsWith(prefix + "sus")) {
            Commands.misc.Sus(message, channel);
            print("Command '!sus' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "7")) {
            Commands.misc.Seven(channel);
            print("Command '!7' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "bald")) {
            Commands.misc.Bald(channel);
            print("Command '!bald' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "gordon")) {
            Commands.misc.Gordon(channel);
            print("Command '!gordon' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "monke")) {
            Commands.misc.Monke(channel);
            print("Command '!monke' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "28sw")) {
            Commands.misc.StabWounds(channel);
            print("Command '!28sw' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "brankee")
                || args[0].equals(prefix + "yankee")
                || args[0].equals(prefix + "brim")) {
            Commands.misc.Brankee(channel);
            print("Command '!brankee', '!yankee' or '!brim' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "stupid")) {
            Commands.misc.Stupid(member, channel);
            print("Command '!stupid' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "dora")) {
            Commands.misc.Dora(member, channel);
            print("Command '!dora' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "test")) {
            switch (args[1]) {
                case "start":
                    Commands.test.Start(guild, member, channel);
                    print("Command '!test start' used by " + member.getEffectiveName());
                    break;
                case "stop":
                    Commands.test.Stop(guild, member, channel);
                    print("Command '!test stop' used by " + member.getEffectiveName());
                    break;
                case "done":
                    Commands.test.Done(guild, member, channel);
                    print("Command '!test done' used by " + member.getEffectiveName());
                    break;
                default:
                    if (testIsStarted) channel.sendMessage("The test is currently going").queue();
                    else channel.sendMessage("No test is on right now").queue();
                    break;
            }
        }

//        else if (args[0].contains(prefix + "py"))
//            Commands.pro.Python(event);

//        else if (args[0].equals(prefix + "java"))
//            Commands.pro.Java(event);
        
//        else if (args[0].equals(prefix + "testing")) {
//            try {
//                if (args[1] != null) {
//                    if (args[1].equals("start") || args[1].equals("true")) {
//                        isTesting = true;
//                        channel.sendMessage("Testing mode started").queue();
//
//                    } else if (args[1].equals("stop") || args[1].equals("false")) {
//                        isTesting = false;
//                        channel.sendMessage("Testing mode stopped").queue();
//
//                    } else {
//                        if (isTesting) channel.sendMessage("CSBot is currently in testing mode").queue();
//                        if (!isTesting) channel.sendMessage("CSBot is currently not in testing mode").queue();
//                    }
//                }
//            } catch (Exception e) {
//                if (isTesting) channel.sendMessage("CSBot is currently in testing mode").queue();
//                if (!isTesting) channel.sendMessage("CSBot is currently not in testing mode").queue();
//            }
//        }
    }

    protected void print(String s) {
        System.out.println(s);
    }

}
