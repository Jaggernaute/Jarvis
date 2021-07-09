package fr.jaggernaute.jarvis;

import fr.jaggernaute.jarvis.lavaplayer.MainMusic;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class Listener extends ListenerAdapter
{
    private final char prefix = '!';
    MainMusic mainMusic = new MainMusic();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] splitCommand = event.getMessage().getContentRaw().split("^!", 2);
        if (event.getAuthor().isBot()) return;
        if(event.getMessage().getContentRaw().isEmpty())return;
        if(event.getMessage().getContentRaw().charAt(0) == prefix) {
            String[] splitargs = splitCommand[1].split(" ", 2);
            switch (splitargs[0]) {
                case "ping" -> pong(event);
                case "my" -> cabbages(event.getChannel());
                case "pig" -> pig(event.getChannel());
                case "purge" -> purge(event.getChannel(), parseInt(splitargs[1]));
                case "play" -> mainMusic.loadAndPlay(event.getChannel(), splitargs[1]);
                case "help" -> help(event.getChannel());

                default -> cmdNotFound(event.getChannel());
            }
        }else return;

        super.onGuildMessageReceived(event);
    }

    private void pong(GuildMessageReceivedEvent e){
        long time = System.currentTimeMillis();
        e.getChannel().sendMessage("Pong!")
                .queue(response-> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });
    }

    private void cabbages(TextChannel channel){
        channel.sendMessage("<:cabbage:862653024747651084>").queue();
    }

    private void pig(TextChannel channel){
        channel.sendMessage(":pig:").queue();
    }

    private void purge(TextChannel channel, int amount) {
        channel.getIterableHistory()
                .takeAsync(amount)
                .thenAccept(channel::purgeMessages);

        channel.sendMessage("Purging channel!")
                .queue(response-> {
                    response.editMessageFormat(amount + " messages have been purges").queue();
                });
    }

    private void help(TextChannel channel){
        EmbedBuilder eb = new EmbedBuilder();
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
        channel.sendMessage(embed).queue();
    }

    private void cmdNotFound(TextChannel channel){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("This command doesn't exist !", null);
        eb.setColor(new Color(153, 48, 84));
        eb.setDescription("The command you are trying to call is not implemented yet");
        eb.addField("For help try :", "``!help``", true);
        eb.addField("To contact my dumbass of a dev :", "``!contact``", true);
        eb.addBlankField(false);
        eb.setAuthor(null, null, null);
        eb.setFooter("Author: Jaggernaute", "https://emoji.gg/assets/emoji/8649_FoxxoTail.gif");
        eb.setImage(null);
        eb.setThumbnail("https://emoji.gg/assets/emoji/5620_FoxxoReally.png");

        MessageEmbed embed = eb.build();
        channel.sendMessage(embed).queue();
    }
}