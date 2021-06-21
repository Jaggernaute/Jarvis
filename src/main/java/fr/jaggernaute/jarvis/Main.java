package fr.jaggernaute.jarvis;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


public class Main
{
    public static void main(String[] arguments) throws LoginException, InterruptedException {

        String token = "NzY0NTQyNjM1NTYxODQ0NzM2.X4Hx0A.eBo2YSk5pyZd2lzQ8MY0z3C3Bsw";

        JDA api = JDABuilder.createDefault(token).addEventListeners(new Listener()).build().awaitReady();
    }
}


