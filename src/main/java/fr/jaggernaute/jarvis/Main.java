package fr.jaggernaute.jarvis;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import io.github.cdimascio.dotenv.Dotenv;

import javax.security.auth.login.LoginException;
import java.util.Map;


public class Main
{
    public static void main(String[] arguments) throws LoginException, InterruptedException {

        System.out.println("   __                   _             ___ _                 \n" +
                "   \\ \\  __ _ _ ____   _(_)___        / __\\ |__   __ _ _ __  \n" +
                "    \\ \\/ _` | '__\\ \\ / / / __|_____ / /  | '_ \\ / _` | '_ \\ \n" +
                " /\\_/ / (_| | |   \\ V /| \\__ \\_____/ /___| | | | (_| | | | |\n" +
                " \\___/ \\__,_|_|    \\_/ |_|___/     \\____/|_| |_|\\__,_|_| |_|\n" +
                "                                                            ");

        Dotenv dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");

        JDA api = JDABuilder.createDefault(token)
                .addEventListeners(new Listener())
                .build()
                .awaitReady();

        System.out.println("\033[0;32m"+"Jarvis-Chan is online masta !");
    }
}


