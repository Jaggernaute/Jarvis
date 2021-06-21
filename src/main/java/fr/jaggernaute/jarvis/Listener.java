package fr.jaggernaute.jarvis;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        TextChannel channel = event.getGuild().getTextChannelById("856492061780213761");
        if (event.getAuthor().isBot()) return;

        if (event.getChannel().equals(channel)) {
            Message message = event.getMessage();
            String content = message.getContentRaw();
            if (content.equals("!ping"))
            {
                channel.sendMessage("Pong!").queue();
            }
        }
    }
}
