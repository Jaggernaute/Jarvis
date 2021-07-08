package fr.jaggernaute.jarvis;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MESSAGES;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_VOICE_STATES;


public class Main
{
    public static void main(String[] arguments) throws Exception {

        Dotenv dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");

        JDA api = JDABuilder.create((token), GUILD_MESSAGES, GUILD_VOICE_STATES)
                .addEventListeners(new Listener())
                .setActivity(Activity.playing("!help"))
                .build();
    }

}


