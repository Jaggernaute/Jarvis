package fr.jaggernaute.jarvis.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

class Speak implements Runnable{
    final String[] text;
    final GuildMessageReceivedEvent e;
    final User author;

    public Speak(String[] text, GuildMessageReceivedEvent e) {
        this.text = text;
        this.e = e;
        this.author = e.getMessage().getAuthor();
    }

    public void run() {
        e.getChannel().sendMessage(text[1]).queue();
        System.out.println(author.getName() + " request Jarvis-Chan to say " + text[1]);
    }
}

public class Say {
    public void say(String[] text, GuildMessageReceivedEvent e) {
        Speak speak = new Speak(text, e);
        Thread t = new Thread(speak);
        t.start();
    }
}
