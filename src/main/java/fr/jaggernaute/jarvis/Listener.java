package fr.jaggernaute.jarvis;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;


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
                channel.sendMessage("Fuck !").queue();
            }else if (content.equals("!help"))
            {
                EmbedBuilder eb = new EmbedBuilder();

                eb.setTitle("Jarvis help center", null);

                eb.setColor(Color.blue);
                eb.setColor(new Color(0xC13F3F));
                eb.setColor(new Color(184, 38, 66));

                eb.setDescription("Command list:");
                eb.addField("!ping", "return : Pong! (for debug purposes only)", true);
                eb.addField("!help", "Show this panel", true);

                eb.addBlankField(false);
                eb.setAuthor("Help", null, "https://imgur.com/mN81ttG.png");

                LocalDateTime localDate = LocalDateTime.now();
                DateTimeFormatter myFormatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDate = localDate.format(myFormatDay);
                DateTimeFormatter myFormatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = localDate.format(myFormatTime);
                eb.setFooter("Sent by Jarvis the " + formattedDate + " at " + formattedTime);

                eb.setImage("https://i.imgur.com/aJajRjp.png");
                eb.setThumbnail("https://imgur.com/iWdqJ0S.png");
                channel.sendMessage(eb.build())
                        .queue();
            }else if(content.equals("!music")){

                Guild guild = event.getGuild();
                VoiceChannel voiceChannel = guild.getVoiceChannelsByName("vocal", true).get(0);
                AudioManager audioManager = guild.getAudioManager();
                audioManager.openAudioConnection(voiceChannel);
            }
        }
    }
}
