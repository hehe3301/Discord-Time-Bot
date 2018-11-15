package com.github.hehe3301;

import com.github.hehe3301.bot.CommandHandler;
import com.github.hehe3301.test.MockDiscordClient;
import com.github.hehe3301.test.MockMessage;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Scanner;

public class CliTestRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandHandler mockBot = new CommandHandler();

        IDiscordClient client = new MockDiscordClient();
        client.getDispatcher().registerListener(mockBot);
        client.login();

        System.out.println("Entering Mock Message Mode (type \"quit\" to quit).");
        while (true) {
            String userCommand = in.nextLine();
            if ("quit".equals(userCommand)) {
                System.exit(0);
            }
            IMessage mockMessage = new MockMessage(userCommand);
            MessageReceivedEvent mockEvent = new MessageReceivedEvent(mockMessage);
            client.getDispatcher().dispatch(mockEvent);
        }
    }
}
