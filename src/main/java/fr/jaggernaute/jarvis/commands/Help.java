package fr.jaggernaute.jarvis.commands;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

class HelpBuilder implements Runnable{
    final GuildMessageReceivedEvent e;
    final User author;

    public HelpBuilder(GuildMessageReceivedEvent e) {
        this.e = e;
        this.author = e.getMessage().getAuthor();
    }

    public void run() {
        net.dv8tion.jda.api.EmbedBuilder eb = new net.dv8tion.jda.api.EmbedBuilder();
        eb.setTitle("Help menu:", null);
        eb.setColor(new Color(153, 48, 84));
        eb.setDescription("This menu will help you understand the high level of completute of tis bot !");
        eb.addField("``!ping :``", "ping the bot and tell you the latency", true);
        eb.addField("``!play <url> :``", "play a song", true);
        eb.addField("``!skip :``", "ping the bot and tell you the latency", true);
        eb.addBlankField(false);
        eb.setAuthor(null, null, null);
        eb.setFooter("Author: Jaggernaute", "https://emoji.gg/assets/emoji/8649_FoxxoTail.gif");
        eb.setImage(null);
        eb.setThumbnail("https://emoji.gg/assets/emoji/5620_FoxxoReally.png");

        MessageEmbed embed = eb.build();
        e.getChannel().sendMessage(embed).queue();

        System.out.println(author.getName() + " request the help panel");
    }
}

public class Help {
    public void help(GuildMessageReceivedEvent e) {
        HelpBuilder help = new HelpBuilder(e);
        Thread t = new Thread(help);
        t.start();
    }
}