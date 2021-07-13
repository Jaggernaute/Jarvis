package fr.jaggernaute.jarvis;

import fr.jaggernaute.jarvis.commands.*;
import fr.jaggernaute.jarvis.lavaplayer.MainMusic;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class Listener extends ListenerAdapter {
    private final char prefix = '!';
    MainMusic mainMusic = new MainMusic();
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        //regex to cut the '!'
        String[] splitCommand = event.getMessage().getContentRaw().split("^!", 2);
        //checks if: the author is a bot / the message is empty / the message start with a '!'
        if (event.getAuthor().isBot()) return;
        if (event.getMessage().getContentRaw().isEmpty()) return;
        if (event.getMessage().getContentRaw().charAt(0) == prefix) {
            //regex to split the command into 2 arguments
            String[] splitargs = splitCommand[1].split(" ", 2);
            //switch the command and execute the associated method
            switch (splitargs[0]) {
                case "ping" -> {
                    Ping ping = new Ping();
                    ping.ping(splitargs, event);
                }
                case "my" -> cabbages(event);
                case "pig" -> pig(event);
                case "purge" -> purge(event, parseInt(splitargs[1]));
                case "play" -> mainMusic.loadAndPlay(event.getChannel(), splitargs[1]);
                case "help" -> {
                    Help help = new Help();
                    help.help(event);
                }
                case "timer" -> {
                    TimerStart timer = new TimerStart();
                    timer.timer(event, splitargs);
                }
                case "say" ->{
                    Say say = new Say();
                    say.say(splitargs, event);
                }
                default -> cmdNotFound(event);
            }
        } else return;

        super.onGuildMessageReceived(event);
    }

    private void cabbages(GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage("<:cabbage:862653024747651084>").queue();
    }

    private void pig(GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage(":pig:").queue();
    }

    private void purge(GuildMessageReceivedEvent e, int amount) {
        e.getChannel().getIterableHistory()
                .takeAsync(amount)
                .thenAccept(e.getChannel()::purgeMessages);

        e.getChannel().sendMessage("Purging channel!")
                .queue(response -> {
                    response.editMessageFormat(amount + " messages have been purges").queue();
                });
    }

    private void cmdNotFound(GuildMessageReceivedEvent e) {
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
        e.getChannel().sendMessage(embed).queue();
    }
}

