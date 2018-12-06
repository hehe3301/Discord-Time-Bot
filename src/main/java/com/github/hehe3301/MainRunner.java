package com.github.hehe3301;

import com.github.hehe3301.bot.BotUtils;
import com.github.hehe3301.bot.CommandHandler;
import com.github.hehe3301.configs.Configuration;
import com.github.hehe3301.configs.Secrets;
import com.github.hehe3301.configs.Settings;
import com.github.hehe3301.time_handler.TimeHandler;
import sx.blah.discord.api.IDiscordClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class MainRunner
{
    public static void main(String[] args)
    {
        String configPath = (args.length >= 1)
                ? args[0]
                : "config.yaml";

        File configFile = new File(configPath);
        Configuration config;
        try {
            config = Configuration.from(new FileInputStream(configFile));
        } catch (FileNotFoundException fileEx) {
            Configuration baseConfig = Configuration.defaults();
            try {
                Files.write(configFile.toPath(), baseConfig.toYaml().getBytes());
                System.out.println("No config found, a default was generated. Make sure to set the bot token!");
            } catch (IOException ioEx) {
                String reply = String.format("Unable to locate or create config file at %s.", configPath);
                System.out.println(reply);
            }
            return;
        }

        if (config.bot_token == null)
        {
            String reply = String.format("Bot token is blank. Please check bot_token in %s.", configPath);
            System.out.println(reply);
            return;
        }

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(config.bot_token);

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        TimeHandler th = new TimeHandler();
        CommandHandler ch = new CommandHandler(config.com_prefix, th, config.debug_enabled);
        cli.getDispatcher().registerListener(ch);

        // Only login after all events are registered otherwise some may be missed.
        cli.login();
    }

}
