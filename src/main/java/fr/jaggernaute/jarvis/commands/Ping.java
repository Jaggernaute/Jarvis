package fr.jaggernaute.jarvis.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

class PingBuilder implements Runnable{
    final String[] text;
    final GuildMessageReceivedEvent e;
    final User author;
    final long time;

    public PingBuilder(String[] text, GuildMessageReceivedEvent e) {
        this.text = text;
        this.e = e;
        this.author = e.getMessage().getAuthor();
        this.time = System.currentTimeMillis();
    }

    public void run() {
        e.getChannel().sendMessage("Pong!")
                .queue(response -> response.editMessageFormat(
                        "Pong: %d ms", System.currentTimeMillis() - time).queue()
                    );
        System.out.println(author.getName() + " ping the bot (the ping is " + (System.currentTimeMillis() - time) + "ms)");
    }
}

public class Ping {
    public void ping(String[] text, GuildMessageReceivedEvent e) {
        PingBuilder messageBuilder = new PingBuilder(text, e);
        Thread t = new Thread(messageBuilder);
        t.start();
    }
}