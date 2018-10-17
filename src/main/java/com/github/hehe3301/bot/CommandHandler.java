package com.github.hehe3301.bot;


import com.github.hehe3301.configs.Settings;
import com.github.hehe3301.conditional_print.CP;
import com.github.hehe3301.time_handler.TimeHandler;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

import java.util.*;

/**
 * Created by declan on 04/04/2017.
 */
public class CommandHandler {

    public TimeHandler time_handler;


    // A static map of commands mapping from command string to the functional impl
    private static Map<String, Command> commandMap = new HashMap<>();
    private static Map<String, String> helpMap = new HashMap<>();

    public CommandHandler() {
        this.time_handler = new TimeHandler();
        helpMap.put("now",Settings.com_prefix+"now prints the current time in UTC if not called with a parameter, or in the given TZ's if they exists.");
        commandMap.put("now", (event, args) -> {

            CP.cLog(Settings.debug_enabled, "User: "+event.getAuthor().getName()+" instructed me to get current time.\n" );
            String rtn = "";
            if (args.isEmpty())
            {
                rtn="\n"+time_handler.now();
            }
            else
            {
                for (String tz : args)
                {
                    rtn=rtn+"\n"+time_handler.now(tz);
                }
            }

            event.getChannel().sendMessage(event.getAuthor().mention()+rtn);


        });

        helpMap.put("help",Settings.com_prefix+"help prints this help message.");
        commandMap.put("help", (event, args) -> {

            CP.cLog(Settings.debug_enabled, "User: "+event.getAuthor().getName()+" printed the help.\n" );

            String printString = "Commands I know:";
            for (String key : commandMap.keySet())
            {
                printString=printString+"\n"+key+": "+helpMap.get(key);
            }
            event.getChannel().sendMessage(event.getAuthor().mention()+"\n"+printString);

        });

        helpMap.put("alias",Settings.com_prefix+"alias adds a alias to a timezone(s), standard is required but daylight savings is optional. ex: alias Eastern EST EDT"+" UNIMPLEMENTED");
        commandMap.put("alias", (event, args) -> {
            //TODO implement
            event.getChannel().sendMessage(event.getAuthor().mention()+"\n"+"This feature has not been implemented yet!");

        });
        helpMap.put("aliases",Settings.com_prefix+"aliases prints the list of known aliases");
        commandMap.put("aliases", (event, args) -> {
            String printString = time_handler.getAliases();

            event.getChannel().sendMessage(event.getAuthor().mention()+"\n"+printString);

        });

        helpMap.put("time",Settings.com_prefix+"time translates a time from one timezone to another. ex: time 7pm est utc"+" UNIMPLEMENTED");
        commandMap.put("time", (event, args) -> {

            //TODO implement
            event.getChannel().sendMessage(event.getAuthor().mention()+"\n"+"This feature has not been implemented yet!");

        });
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply incase they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand" doesn't exist in
        // most situations. It's partially good practise and partially developer preference

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]
        String[] argArray = event.getMessage().getContent().split(" ");

        // First ensure at least the command and prefix is present, the arg length can be handled by your command func
        if(argArray.length == 0)
            return;

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if(!argArray[0].startsWith(Settings.com_prefix))
            return;

        // Extract the "command" part of the first arg out by ditching the amount of characters present in the prefix
        String commandStr = argArray[0].substring(Settings.com_prefix.length());

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        // Instead of delegating the work to a switch, automatically do it via calling the mapping if it exists

        if(commandMap.containsKey(commandStr))
            commandMap.get(commandStr).runCommand(event, argsList);

    }

}
