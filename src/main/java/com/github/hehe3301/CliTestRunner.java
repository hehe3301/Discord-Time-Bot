package com.github.hehe3301;
import com.github.hehe3301.bot.BotUtils;
import com.github.hehe3301.bot.CommandHandler;
import com.github.hehe3301.test.MockDiscordClient;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import java.util.Scanner;

public class CliTestRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandHandler mockBot = new CommandHandler();

        IDiscordClient client = new MockDiscordClient();
        client.getDispatcher().registerListener(new CommandHandler());
        client.login();


        System.out.print("Enter a name: ");
        String name = in.nextLine().trim();
        String greeting = "Hello, %s!";
        System.out.println(String.format(greeting, name));
    }
}
