package com.shadow;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {
    EmbedBuilder eb;

    public Embed(String title, Color color, String description, String footer) {
        eb = new EmbedBuilder()
                .setTitle(title)
                .setColor(color)
                .setDescription(description)
                .setFooter(footer);
    }

    public void addField(String title, String text, boolean inline) {
        eb.addField(title, text, inline);
    }

    public void addBlank() {
        eb.addBlankField(false);
    }

    public MessageEmbed build() {
        return eb.build();
    }

}
