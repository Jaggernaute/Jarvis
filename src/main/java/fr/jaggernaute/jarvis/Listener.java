package fr.jaggernaute.jarvis;

import com.vdurmont.emoji.EmojiParser;
import fr.jaggernaute.jarvis.lavaplayer.MainMusic;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter
{
    private final char prefix = '!';
    MainMusic mainMusic = new MainMusic();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] splitCommand = event.getMessage().getContentRaw().split("^!", 2);
        if (event.getAuthor().isBot()) return;
        if(event.getMessage().getContentRaw().charAt(0) == prefix) {
            String[] splitargs = splitCommand[1].split(" ", 2);
            switch (splitargs[0]) {
                case "ping" -> pong(event.getChannel(), event);
                case "my" -> cabbages(event.getChannel());
                case "pig" -> pig(event.getChannel());
                case "play" -> mainMusic.loadAndPlay(event.getChannel(), splitargs[1]);
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
}