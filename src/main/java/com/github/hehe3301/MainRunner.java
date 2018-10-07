package com.github.hehe3301;

import com.github.hehe3301.bot.BotUtils;
import com.github.hehe3301.bot.CommandHandler;
import com.github.hehe3301.configs.Secrets;
import sx.blah.discord.api.IDiscordClient;

public class MainRunner {

    public static void main(String[] args){

        if(args.length != 1){
            System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
            IDiscordClient cli = BotUtils.getBuiltDiscordClient(Secrets.getInstance().BOT_TOKEN);

            // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
            cli.getDispatcher().registerListener(new CommandHandler());

            // Only login after all events are registered otherwise some may be missed.
            cli.login();
            return;
        }

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new CommandHandler());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();

    }

}
