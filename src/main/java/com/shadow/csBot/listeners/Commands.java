package com.shadow.csBot.listeners;

import com.shadow.Embed;
import com.shadow.csBot.Const;
import com.shadow.csBot.Logger;
import com.shadow.csBot.SlackMessage;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import static com.shadow.csBot.SlackMessage.send;

class Commands {
    public static void Help(User user) {
        try {
            user.openPrivateChannel().queue(channel -> {
                Embed eb = new Embed("CSBot help",
                        Color.orange,
                        "All of the following commands have a '!' before them.",
                        "*Note: There are some hidden commands and commands that are for admins only that aren't listed here.");

//                eb.addField("!help", "gives you a dm with help", false);
                eb.addField("!ping", "sends pong", false);
                eb.addField("!alert", "Alerts server owner", false);
                eb.addField("!spell", "casts a magic spell", false);
                eb.addField("!choose <users>", "chooses 1 of the mentioned users", false);
                eb.addField("!d <sides> <rolls>", "rolls a dice of the defined sides a defined number of times", true);
                eb.addField("!sus <users", "chooses 1 of the mentioned users", false);
                eb.addField("!7", "sends 7", false);
                eb.addField("!bald", "sends pictures of bald", false);
                eb.addField("!gordon", "sends gordon", false);
                eb.addField("!monke", "sends monke pictures", false);
                eb.addField("!28sw", "sends Connor", false);
                eb.addField("!brankee", "Brim with no yankee or yankee with no brim?", false);
                eb.addField("!kachow", "Lightning Mcqueen", false);
                eb.addField("!tonk", "T O N K", false);
                eb.addField("!py\\`\\`\\` <code> \\`\\`\\`", "you can run python code", false);


                channel.sendMessage(eb.build()).queue();
            });
        } catch (IllegalStateException ignored) {}
    }

    public static void PingPong(MessageChannel channel) {
        channel.sendMessage("pong!").queue();
    }

    public static void Announce(Guild g, Member member, Message message, MessageChannel channel) {
        if (member.getRoles().contains(member.getGuild().getRoleById(Const.ADMIN_ID))) {

            String args = message.getContentRaw().replace("!announce", "");
            channel.sendMessage("Announcing ```" + args + "```").queue();
            Objects.requireNonNull(
                    g.getTextChannelById(Const.ANNOUNCEMENT_CHANNEL))
                    .sendMessage("<@everyone> " + args).queue();
        }
    }

    public static void Alert(Member member, MessageChannel channel) {
        send("You are needed by " + member.getEffectiveName());
        channel.sendMessage("Summoning Christian").queue();
    }

    public static void Spell(MessageChannel channel) {
        String[] spells = {
                "Wingardium leviosa!",
                "Accio!",
                "begone T H O T T E R!!!",
                "Garvi is so cool \ud83d\udc4d",
                "mY mAgIc Is So PoWeRfUlLlL!!",
                "Infinite P O W E R!!!!!!!",
                "Yeetus\nDeletus\nDeletus\nThy\nFetus",
                "Bippity boppity, you're account's now my property",
                "ERROR 404: spell not found"
        };

        Random r = new Random();
        int a = r.nextInt(spells.length - 1);
        channel.sendMessage(spells[a]).queue();
    }

    public static void Choose(Message message, MessageChannel channel) {
        Random r = new Random();
        int a = r.nextInt(message.getMentionedMembers().size());

        if (message.getMentionedMembers().get(a).getNickname() != null) {
            channel.sendMessage(message
                    .getMentionedMembers()
                    .get(a).getNickname()
                    + " is the winner")
                    .queue();
        } else {
            channel.sendMessage(message
                    .getMentionedMembers()
                    .get(a).getEffectiveName()
                    + " is the winner")
                    .queue();
        }
    }

    public static void Dice(String[] args, MessageChannel channel) {

        int dice = Integer.parseInt(args[1]);
        int rolls = Integer.parseInt(args[2]);

        int[] count = new int[dice];

        Random r = new Random();
        for (int i = 0; i < rolls; i++) {
            int a = r.nextInt(dice);
            count[a]++;
        }

        try {
            channel.sendMessage(Arrays.toString(count)).queue();
        } catch (Exception ignored) {
            channel.sendMessage("Could not display answers:\ntoo many characters!\nplease use smaller numbers").queue();
        }

    }

    public static void Idea(Message message, MessageChannel channel) {
        Logger logger = new Logger("csbot_Ideas");
        String tmp = message.getContentRaw().replace("!idea", "");
        if (!tmp.isEmpty()) {
            logger.WriteIdea(message.getAuthor().getName() + ":   " + tmp + "\n");
            channel.sendMessage("thanks for the Idea!``` " + tmp + " ```").queue();
        } else {
            channel.sendMessage("invalid idea, Usage: \"!idea CSBot needs more commands\"").queue();
        }
    }

    public static class misc {
        public static void Sus(Message message, MessageChannel channel) {
            Random r = new Random();
            int a = r.nextInt(message.getMentionedMembers().size() - 1);
            if (message.getMentionedMembers().get(a).getNickname() != null) {
                channel.sendMessage(message
                        .getMentionedMembers()
                        .get(a).getNickname()
                        + " is sus").queue();
            } else {
                channel.sendMessage(message
                        .getMentionedMembers()
                        .get(a).getEffectiveName()
                        + " is sus")
                        .queue();
            }
        }

        public static void Seven(MessageChannel channel) {
            channel.sendMessage("7").queue();
        }

        public static void Bald(MessageChannel channel) {
            channel.sendMessage("https://www.thesun.co.uk/wp-content/uploads/2019/06/GettyImages-626510790-e1560309781212.jpg").queue();
            channel.sendMessage("https://vignette.wikia.nocookie.net/baldis-basics-random-map-series/images/5/5e/Baldi_high_q" +
                    "uality.png/revision/latest/top-crop/width/360/height/450?cb=20200126032638").queue();
        }

        public static void Gordon(MessageChannel channel) {
            channel.sendMessage("You IDIOT SANDWICH!!!").queue();
        }

        public static void Monke(MessageChannel channel) {
            String[] monkeys = {
                    "https://cdn.discordapp.com/attachments/725441827666133122/738239640104075386/image0.jpg",
                    "https://tenor.com/view/dancing-orangutan-monkey-gif-7729061",
                    "https://tenor.com/view/monkey-pissed-mad-angry-annoyed-gif-4691438",
                    "https://tenor.com/view/monkey-monkey-steals-monkey-swipes-gif-13449256",
                    "https://tenor.com/view/boat-monkey-driving-monkey-monkey-driving-boat-funny-monkey-gif-5232687",
                    "https://tenor.com/view/monkey-beauty-funny-animals-gif-8057241",
                    "https://tenor.com/view/full-house-wake-up-smile-monkey-sleeping-gif-15878099",
                    "https://tenor.com/view/obese-monkey-fat-monkey-summer-belly-eating-lettuce-summer-look-gif-13014350",
                    "https://tenor.com/view/spike-funny-animals-monkey-grumpy-not-impressed-gif-10938327",
                    "https://tenor.com/HgsC.gif"
            };

            Random r = new Random();
            int a = r.nextInt(monkeys.length - 1);

            channel.sendMessage(monkeys[a]).queue();
        }

        public static void StabWounds(MessageChannel channel) {
            channel.sendMessage("https://media.discordapp.net/attachments/768126943357829241/773361364390903838/giphy.gif").queue();
            channel.sendMessage("28 STAB WOUNDS, YOU DIDN'T WANT TO LEAVE HIM A CHANCE, HUH? DID YOU FEEL ANGER? HATE? HE WAS " +
                    "BLEEDING, BEGGING YOU FOR MERCY, BUT YOU STABBED HIM, AGAIN AND AGAIN AND AGAIN!... I KNOW YOU KILLED HIM. WHY DON'T YOU " +
                    "SAY IT? JUST SAY \"I KILLED HIM\"! IS IT THAT HARD TO SAY?! JUST SAY YOU KILLED HIM! JUST SAY IT!").queue();

        }

        public static void Brankee(MessageChannel channel) {
            Random r = new Random();
            int a = r.nextInt(2);

            if (a == 0) {
                channel.sendMessage("Yankee with no brim??").queue();
                channel.sendMessage("https://i.imgflip.com/3zqwwk.png").queue();
            }
            else {
                channel.sendMessage("Brim with no yankee??").queue();
                channel.sendMessage("https://static.wikia.nocookie.net/e394b415-efdb-4a90-b5ed-6dec410a4046").queue();
            }
        }

        public static void Kachow(MessageChannel channel) {
            Random r = new Random();
            int a = r.nextInt(3);

            if (a == 0) channel.sendMessage("https://i.redd.it/tm2dbdizvr451.jpg").queue();
            else if (a == 1) channel.sendMessage("https://i.redd.it/9fwky9uj7oc41.jpg").queue();
            else channel.sendMessage("https://upload.wikimedia.org/wikipedia/en/8/82/Lightning_McQueen.png").queue();
        }

        public static void Tonk(MessageChannel channel) {
            channel.sendMessage("T O N K").queue();
        }

        //region Restricted access commands

        public static void Stupid(Member member, MessageChannel channel) {
            if (member.getRoles().contains(member.getGuild().getRoleById(Const.TRUSTED_ID))) {
                String[] answers = {
                        "Mr. Shams is kinda garbo ngl",
                        "Mr. Shams doesn't listen",
                        "I don't like Mr. Shams",
                        "I do believe Mr. Shams doesn't care about us",
                        "S U C C",
                        "the teacher doesn't teach",
                        "Christian teaches more than Mr. Shams",
                        "He's stupid ngl",
                        "I hate him",
                        "Block coding sucks",
                        "Im GoInG tO cAlL yOuR pArEnTs",
                        "Shams gets paid for this?!?!?!?!?!",
                        "Don't forget to use `@GOD` or `!alert` to summon Christian!",
                        "ikr"
                };

                Random r = new Random();
                int a = r.nextInt(answers.length - 1);
                channel.sendMessage(answers[a]).queue();
            }
        }

        public static void Dora(Member member, MessageChannel channel) {
            if (member.getRoles().contains(member.getGuild().getRoleById(Const.TRUSTED_ID))) {
                int a = (int) Math.floor(10 * Math.random());
                channel.sendMessage("Dora and Shams sitting in a tree,").queue();
                channel.sendMessage("```01100110 00100000 01110101 00100000 01100011 00100000 01100011 00100000 01101001 00100000 01101110 00100000 01100111```").queue();
            }
        }

        //endregion
    }

    public static class pro {
         static void Python(MessageReceivedEvent event) {
             String[] textAsString = event.getMessage().getContentRaw().split("\n");
             ArrayList<String> program = new ArrayList<>();

             /* Remove extra pieces of text */
             for (int i = 0; i < textAsString.length; i++) {
                 if (i != 0)
                     if (textAsString[i].contains("```")) {
                         textAsString[i] = textAsString[i].replace("```", "");
                         program.add(textAsString[i]);
                         break;
                     }
                     else
                         program.add(textAsString[i]);

             }

             System.out.println("Python Program: \n" + program);


             File pyProgram = new File("tmp_programs\\tmp.py");
             try {
                 if (pyProgram.createNewFile()) {
                     System.out.println("File created: " + pyProgram.getName());
                     PrintPythonCode(event, pyProgram, program);

                 } else {
                     System.out.println("File already exists, deleting and recreating");
                     if (pyProgram.delete()) {
                         System.out.println("file deleted");
                         if (pyProgram.createNewFile()) {
                             System.out.println("file created: " + pyProgram.getName());
                             PrintPythonCode(event, pyProgram, program);
                         }
                     } else {
                         System.out.println("Delete failed");
                         send("Deletion of tmp python file failed");
                     }
                 }
             } catch (IOException e) {
                 System.out.println("An error occurred:");
                 send("An IOException occurred");
                 e.printStackTrace();
             }
         }

         static void PrintPythonCode(MessageReceivedEvent event, File file, ArrayList<String> program) {
             try {
                 FileWriter programWriter = new FileWriter("tmp_programs\\" + file.getName());

                 System.out.println("Writing to: tmp_programs\\" + file.getName());

                 for (String s : program) {
                     programWriter.write(s + "\n");
                     programWriter.flush();
                 }

                 System.out.println("Done writing to: tmp_programs\\" + file.getName());
                 programWriter.close();

                 String s = "";
                 StringBuilder out = new StringBuilder();
                 Process p = Runtime.getRuntime().exec("python tmp_programs\\" + file.getName());
                 BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                 
                 while (s != null) {
                     System.out.println(s);
                     out.append(s).append(" ");
                     s = stdInput.readLine();
                 }
                 
                 System.out.println(out);
                 System.out.println("done");

                 try {
                     event.getChannel().sendMessage(out.toString()).queue();
                 }
                 catch (IllegalArgumentException e) {
                     event.getChannel().sendMessage("The command could not be run\nYou probably made a syntax error").queue();
                 }

             } catch (IOException e) {
                 System.out.println("An error occurred");
                 send("An IOException occurred");
                 e.printStackTrace();
             }

         }

         // TODO: finish java processing and stuff
        //region Start of Java code interpretation

        public static void Java(MessageReceivedEvent event) {
            String[] textAsString = event.getMessage().getContentRaw().split("\n");
            ArrayList<String> program = new ArrayList<>();

            /* Remove extra pieces of text */
            for (int i = 0; i < textAsString.length; i++) {
                if (i != 0)
                    if (textAsString[i].contains("```")) {
                        textAsString[i] = textAsString[i].replace("```", "");

                        if (textAsString[i].equals(""))
                            break;
                        else
                            program.add(textAsString[i]);
                        break;
                    }
                    else
                        program.add(textAsString[i]);

            }

            System.out.println("Java Program: \n" + program);

            File javaProgram = new File("tmp_programs\\java\\tmp.java");
            try {
                if (javaProgram.createNewFile()) {
                    System.out.println("File created: " + javaProgram.getName());
                    PrintJavaCode(event, javaProgram, program);

                } else {
                    System.out.println("File already exists, deleting and recreating");
                    if (javaProgram.delete()) {
                        System.out.println("file deleted");
                        if (javaProgram.createNewFile()) {
                            System.out.println("file created: " + javaProgram.getName());
                            PrintJavaCode(event, javaProgram, program);
                        }
                    } else {
                        System.out.println("Delete failed");
                        send("Deletion of tmp python file failed");
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred:");
                send("An IOException occurred:\n" + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void PrintJavaCode(MessageReceivedEvent event, File file, ArrayList<String> program) {
            try {
                FileWriter programWriter = new FileWriter("tmp_programs\\java\\" + file.getName());

                System.out.println("Writing to: tmp_programs\\java\\" + file.getName());
                programWriter.write(" class tmp {\n");
                programWriter.flush();
                for (String s : program) {
                    programWriter.write("\t" + s + "\n");
                    programWriter.flush();
                }
                programWriter.write("\n}");
                System.out.println("Done writing to: tmp_programs\\java\\" + file.getName());
                programWriter.close();

                String s;
                StringBuilder _out = new StringBuilder();

                System.out.println("Compiling program: " + file.getName());
                Runtime.getRuntime().exec("javac tmp_programs\\java\\" + file.getName());

                System.out.println("Running program: " + file.getName().replace(".java", ""));
                Process p = Runtime.getRuntime().exec("java tmp_programs\\java\\" + file.getName().replace(".java", ""));
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                    _out.append(s);
                    _out.append(" ");
                }

                try {
                    event.getChannel().sendMessage(_out).queue();
                }
                catch (IllegalArgumentException e) {
                    event.getChannel().sendMessage("The command could not be run\nYou probably made a syntax error").queue();
                }

            } catch (IOException e) {
                System.out.println("An error occurred");
                send("An IOException occurred:\n" + e.getMessage());
                e.printStackTrace();
            }
        }

        //endregion
    }

    public static class test {
        public static void Start(Guild g, Member member, MessageChannel channel) {
            if (member.getId().equals(Const.OWNER_ID)) {
                if (!Listener.testIsStarted) {
                    Stop(g, member, channel, false); // Deletes all "done" tags

                    Listener.testIsStarted = true;
                    g.createRole()
                            .setMentionable(true)
                            .setHoisted(true)
                            .setName("done")
                            .queue();
                    channel.sendMessage("Test started").queue();
                    SlackMessage.send("Test started");
                } else {
                    channel.sendMessage("Test has already been started").queue();
                }
            }
        }

        public static void Stop(Guild g, Member member, MessageChannel channel, boolean shouldMessage) {
            if (member.getId().equals(Const.OWNER_ID)) {
                g.getRoles().forEach(a -> {
                    if (a.getName().equals("done")) {
                        a.delete().queue();

                        if (shouldMessage) {
                            channel.sendMessage("Test done!").queue();
                            SlackMessage.send("Test stopped");
                        }
                    }
                });
            }
        }

        public static void Done(Guild g, Member member, MessageChannel channel) {
            if (Listener.testIsStarted) {
                List<Role> roles = g.getRoles();
                Role r = null;
                for (Role role : roles) {
                    if (role.getName().equals("done")) {
                        r = role;
                    }
                }
                g.addRoleToMember(member, Objects.requireNonNull(
                        g.getRoleById(Objects.requireNonNull(r).getId())
                )).queue();
                channel.sendMessage(member.getEffectiveName() + " is now done").queue();

            } else {
                channel.sendMessage("No test is started").queue();
            }
        }
    }
}
