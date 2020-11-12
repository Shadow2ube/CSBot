package com.shadow.csBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    String filename = "";
    String path = "\\home\\christian\\csbot\\";
//    String path = "tmp_programs\\";
    public Logger(String filename) {
        this.filename = filename;
        try {
            File ideas = new File(this.path + this.filename + ".txt");
            if (ideas.createNewFile()) {
                System.out.println("File created: " + ideas.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            SlackMessage.SendSlackMessage("Logger file creation failed");
            e.printStackTrace();
        }
    }

    public void WriteIdea(String idea) {
        try {
            FileWriter writer = new FileWriter(this.path + this.filename + ".txt", true);
            writer.write(idea);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
