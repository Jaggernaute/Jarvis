package fr.jaggernaute.jarvis;

import com.vdurmont.emoji.EmojiParser;
import fr.jaggernaute.jarvis.lavaplayer.MainMusic;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static java.lang.Integer.parseInt;

public class Listener extends ListenerAdapter
{
    private final char prefix = '!';
    MainMusic mainMusic = new MainMusic();
    EmbedFormater embedFormater = new EmbedFormater();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] splitCommand = event.getMessage().getContentRaw().split("^!", 2);
        if (event.getAuthor().isBot()) return;
        if(event.getMessage().getContentRaw().isEmpty())return;
        if(event.getMessage().getContentRaw().charAt(0) == prefix) {
            String[] splitargs = splitCommand[1].split(" ", 2);
            switch (splitargs[0]) {
                case "ping" -> pong(event.getChannel(), event);
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

    public void pong(TextChannel channel, GuildMessageReceivedEvent e){
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!")
                .queue(response-> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });
    }

    public void cabbages(TextChannel channel){
        channel.sendMessage("<:cabbage:862653024747651084>").queue();
    }

    public void pig(TextChannel channel){
        String pig = ":pig:";
        String result = EmojiParser.parseToUnicode(pig);
        channel.sendMessage(result).queue();
    }

    public void purge(TextChannel channel, int amount) {
        channel.getIterableHistory()
                .takeAsync(amount)
                .thenAccept(channel::purgeMessages);

        channel.sendMessage("Purging channel!")
                .queue(response-> {
                    response.editMessageFormat(amount + " messages have been purges").queue();
                });
    }

    public void help(TextChannel channel){
        channel.sendMessage("Fuck U bitch ! I use java ! ☕").queue();
    }

    public void cmdNotFound(TextChannel channel){
        channel.sendMessage(embedFormater.embedFormater("This comand doesn't exist",
                "The command you are trying to call is not implemented yet",
                "For help :",
                "``!help``",
                false,
                null,
                null,
                "Author: Jaggernaute",
                "https://emoji.gg/assets/emoji/8649_FoxxoTail.gif",
                null,
                "https://emoji.gg/assets/emoji/5620_FoxxoReally.png")).queue();
    }
}