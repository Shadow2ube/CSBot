package com.shadow.csBot.listeners;

import com.shadow.csBot.Const;
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

        if (args[0].equalsIgnoreCase("I'm")) {

            StringBuilder _tmp = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equalsIgnoreCase("sad") || args[i].equalsIgnoreCase("gay") || args[i].equalsIgnoreCase("depressed")
                        || args[i].equalsIgnoreCase("gae") || args[i].equalsIgnoreCase("suicidal") || args[i].equalsIgnoreCase("stupid")
                        || args[i].equalsIgnoreCase("dumb")) {

                    event.getChannel().sendMessage("LOL\nI know.").queue();
                    event.getChannel().sendMessage("I know.").queue();
                    return;
                }
                else if (i != 0) {
                    _tmp.append(' ');
                    _tmp.append(args[i]);
                }
            }

            event.getChannel().sendMessage("Hi" + _tmp + ", I'm Dad!").queue();
        }

        for (String arg : args) {
            if (arg.contains("tonk")
                    || arg.contains("thunk")
                    || arg.contains("thonk")
                    || arg.contains("tink")) {
                Commands.misc.Tonk(channel);
            }
        }

        Member[] mentioned = event.getMessage().getMentionedMembers().toArray(new Member[0]);
        for (Member m : mentioned) {
            if (m == event.getGuild().getMemberById(Const.CSBOT_ID)) {
                event.getChannel().sendMessage("You summoned me?").queue();
            }
        }

        //endregion

        if (args[0].equalsIgnoreCase(prefix + "help")) {
            Commands.Help(event.getAuthor());
            print("Command '!help' used by "+ member.getEffectiveName());
        }

        else if (args[0].equalsIgnoreCase(prefix + "ping")) {
            Commands.PingPong(channel);
            print("Command '!ping' used by "+ member.getEffectiveName());

        } else if (args[0].startsWith(prefix + "announce")) {
            Commands.Announce(guild, member, message, channel);
            print("Command '!announce' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "alert")) {
            Commands.Alert(member, channel);
            print("Command '!alert' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "spell")) {
            Commands.Spell(channel);
            print("Command '!spell' used by " + member.getEffectiveName());

        } else if (args[0].startsWith(prefix + "choose")) {
            Commands.Choose(message, channel);
            print("Command '!choose' used by " + member.getEffectiveName());

        } else if (args[0].equals(prefix + "d")) {
            Commands.Dice(args, channel);
            print("Command '!d' used by " + member.getEffectiveName());

        }

//        else if (args[0].startsWith(prefix + "idea"))
//            Commands.Idea(event);

        else if (args[0].startsWith(prefix + "sus")) {
            Commands.misc.Sus(message, channel);
            print("Command '!sus' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "7")) {
            Commands.misc.Seven(channel);
            print("Command '!7' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "bald")) {
            Commands.misc.Bald(channel);
            print("Command '!bald' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "gordon")) {
            Commands.misc.Gordon(channel);
            print("Command '!gordon' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "monke")) {
            Commands.misc.Monke(channel);
            print("Command '!monke' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "28sw")) {
            Commands.misc.StabWounds(channel);
            print("Command '!28sw' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "brankee")
                || args[0].equalsIgnoreCase(prefix + "yankee")
                || args[0].equalsIgnoreCase(prefix + "brim")) {
            Commands.misc.Brankee(channel);
            print("Command '!brankee', '!yankee' or '!brim' used by " + member.getEffectiveName());

        } else if (args[0].contains("kerchew")
                || args[0].contains("kachow")
                || args[0].contains("kerchoo")) {
            Commands.misc.Kachow(channel);
            print("Command '!kerchoo', `kachow`, `kerchew` used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "stupid")) {
            Commands.misc.Stupid(member, channel);
            print("Command '!stupid' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "dora")) {
            Commands.misc.Dora(member, channel);
            print("Command '!dora' used by " + member.getEffectiveName());

        } else if (args[0].equalsIgnoreCase(prefix + "test")) {
            switch (args[1]) {
                case "start":
                    Commands.test.Start(guild, member, channel);
                    print("Command '!test start' used by " + member.getEffectiveName());
                    break;
                case "stop":
                    Commands.test.Stop(guild, member, channel, true);
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
        else if (args[0].startsWith("!")) {
            channel.sendMessage("That is not a command!\nUse `!help` for help").queue();
        }

//        else if (args[0].contains(prefix + "py"))
//            Commands.pro.Python(event);

//        else if (args[0].equalsIgnoreCase(prefix + "java"))
//            Commands.pro.Java(event);

//        else if (args[0].equalsIgnoreCase(prefix + "testing")) {
//            try {
//                if (args[1] != null) {
//                    if (args[1].equalsIgnoreCase("start") || args[1].equalsIgnoreCase("true")) {
//                        isTesting = true;
//                        channel.sendMessage("Testing mode started").queue();
//
//                    } else if (args[1].equalsIgnoreCase("stop") || args[1].equalsIgnoreCase("false")) {
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
