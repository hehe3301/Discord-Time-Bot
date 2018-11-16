package com.github.hehe3301;

import com.github.hehe3301.bot.BotUtils;
import com.github.hehe3301.bot.CommandHandler;
import com.github.hehe3301.configs.Secrets;
import com.github.hehe3301.time_handler.TimeHandler;
import sx.blah.discord.api.IDiscordClient;

public class MainRunner {

    public static void main(String[] args){

        String authToken;
        if(args.length != 1) {
            System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
            authToken = Secrets.BOT_TOKEN;
        } else {
            authToken = args[0];
        }

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(authToken);

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        TimeHandler th = new TimeHandler();
        CommandHandler ch = new CommandHandler(th);
        cli.getDispatcher().registerListener(ch);

        // Only login after all events are registered otherwise some may be missed.
        cli.login();
    }

}
