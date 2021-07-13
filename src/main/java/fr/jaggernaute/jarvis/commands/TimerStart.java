package fr.jaggernaute.jarvis.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import static java.lang.Thread.sleep;

class Timer implements Runnable {
    private final String[] time;
    private final GuildMessageReceivedEvent e;
    private final User author;

    public Timer(String[] time, GuildMessageReceivedEvent e) {
        this.time = time;
        this.e = e;
        this.author = e.getMessage().getAuthor();
    }

    public void run() {
        VerifyArgs verifyArgs = new VerifyArgs();
        if (!verifyArgs.verifyArgs(time, e, author))return;

        int timeNum = Integer.parseInt(time[1]);
        e.getChannel().sendMessage(timeNum + "s timer started").queue();
        System.out.println(author.getName() + " started a " + timeNum + "s timer");
        try {
            sleep(timeNum * 1000L);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        e.getChannel().sendMessage("Time's up !").queue();
        System.out.println(author.getName() + " timer of " + timeNum + "s just stop");
    }
}

public class TimerStart {
    public void timer(GuildMessageReceivedEvent e, String[] time) {
        Timer timer = new Timer(time, e);
        Thread t = new Thread(timer);
        t.start();
    }
}

 class VerifyArgs{
     /**
      * Check the arguments of the command<p>
      *<p>
      * check if :<p>
      * -the second argument is not null<p>
      * -the second argument is an integer<p>
      * -the integer is strictly positive<p>
      *
      * @param time The length of the timer (Type: int > 0)
      * @param e Event (Type: GuildMessageReceivedEvent)
      * @param author The user that trigger the command (Type: User)
      * @return boolean
      */
    public boolean verifyArgs(String[] time, GuildMessageReceivedEvent e, User author) {

        if (time.length < 2) {
            System.out.println(e.getMessage().getAuthor().getName()
                    + " try to use the 'timer' command without 2nd arg execution have been aborted");
            e.getChannel().sendMessage("command argument error please retry with a number like :"
                    + " ``!timer <time in seconds>``").queue();
            return false;
        }

        try {
            Integer.parseInt(time[1]);
        } catch (NumberFormatException ex) {
            System.out.println(e.getMessage().getAuthor().getName()
                    + " started a timer with a non-numerical time of "
                    + time[1]
                    + "s execution have been aborted");
            e.getChannel().sendMessage("``"
                    + time[1]
                    + "``s timer can't be started non-numerical time is not a thing yet !").queue();
        }

        int timeNum = Integer.parseInt(time[1]);

        if (Integer.parseInt(time[1]) < 0) {
            System.out.println(author.getName()
                    + " started a timer with a negative time of "
                    + timeNum
                    + "s execution have been aborted");
            e.getChannel().sendMessage(timeNum
                    + "s timer can't be started negative time is not a thing yet !").queue();
            return false;
        }else if (Integer.parseInt(time[1]) == 0) {
            System.out.println(author.getName()
                    + " started a timer with a negative time of "
                    + timeNum
                    + "s execution have been aborted");
            e.getChannel().sendMessage(timeNum
                    + "s timer can't be started null time's timers are quite useless !").queue();
            return false;
        }
        return true;
    }
}