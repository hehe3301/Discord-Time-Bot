package com.github.hehe3301.bot;

import com.github.hehe3301.configs.Settings;
import com.github.hehe3301.conditional_print.CP;
import com.github.hehe3301.time_handler.TimeHandler;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class CommandHandler implements IListener<MessageReceivedEvent>
{
    // A static map of commands mapping from command string to the functional impl
    private interface Command extends BiConsumer<MessageReceivedEvent, List<String>> {}
    private Map<String, Command> commandMap;
    private Map<String, String> helpMap;

    public CommandHandler(TimeHandler time_handler) {
        String prefix = Settings.com_prefix; //TODO: pass as parameter
        helpMap = new HashMap<>();
        commandMap = new HashMap<>();

        // Command: now
        helpMap.put("now", String.format(
                "%s prints the current time in all supplied timezones," +
                        " or in UTC if none are given.",
                prefix + "now"));
        commandMap.put("now", (event, args) -> {
            CP.cLog(Settings.debug_enabled,
                    String.format(
                            "User: %s instructed me to get current time.\n",
                            event.getAuthor().getName()));
            String reply = nowString(
                    event.getAuthor().mention(),
                    args,
                    time_handler
            );
            event.getChannel().sendMessage(reply);
        });

        // Command: help
        helpMap.put("help", String.format(
                "%s prints this help message.",
                prefix + "help"));
        commandMap.put("help", (event, args) -> {
            CP.cLog(Settings.debug_enabled,
                    String.format(
                            "User: %s printed the help.\n",
                            event.getAuthor().getName()));
            String reply = helpString(
                    event.getAuthor().mention(),
                    helpMap);
            event.getChannel().sendMessage(reply);
        });

        helpMap.put("alias", Settings.com_prefix + "alias adds a alias to a timezone(s), standard is required but daylight savings is optional. ex: alias Eastern EST EDT" + " UNIMPLEMENTED");
        commandMap.put("alias", (event, args) -> {
            //TODO implement
            event.getChannel().sendMessage(event.getAuthor().mention() + "\n" + "This feature has not been implemented yet!");

        });
        helpMap.put("aliases", Settings.com_prefix + "aliases prints the list of known aliases");
        commandMap.put("aliases", (event, args) -> {
            String printString = time_handler.getAliases();

            event.getChannel().sendMessage(event.getAuthor().mention() + "\n" + printString);

        });

        helpMap.put("time", Settings.com_prefix + "time translates a time from one timezone to another. ex: time 7pm est utc" + " UNIMPLEMENTED");
        commandMap.put("time", (event, args) -> {

            //TODO implement
            event.getChannel().sendMessage(event.getAuthor().mention() + "\n" + "This feature has not been implemented yet!");

        });

        helpMap.put("zones",Settings.com_prefix+"zones dumps all the known time zones!");
        commandMap.put("zones", (event, args) -> {

            String printString = time_handler.dumpZones();
            event.getChannel().sendMessage(event.getAuthor().mention()+"\n"+printString);

        });
    }

    @Override
    public void handle(MessageReceivedEvent event) {

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
        if(!commandMap.containsKey(commandStr)) return;

        commandMap.get(commandStr).accept(event, argsList);
    }

    /**
     * Reply with the current time in all supplied timezones, or in
     * UTC by default.
     * @param authorMention The user's @mention code.
     * @param timeZones The set of time zones to check.
     * @param timeHandler The timezone utility object.
     * @return A formatted reply string.
     */
    private static String nowString(
            String authorMention,
            Collection<String> timeZones,
            TimeHandler timeHandler) {

        String nowOutput;
        if (timeZones.isEmpty()) {
            nowOutput = timeHandler.now();
        } else {
            nowOutput = timeZones.stream()
                    .map(timeHandler::now)
                    .collect(Collectors.joining("\n"));
        }

        return authorMention + "\n" + nowOutput;
    }

    /**
     * Concatenate all the help text into a multi-line string.
     * @param authorMention The user's @mention code.
     * @param helpMap The helpMap object.
     * @return The help reply string.
     */
    private static String helpString(
            String authorMention,
            Map<String, String> helpMap) {
        String helpText = helpMap.entrySet().stream()
                .map(entry -> String.format("%s: %s",
                        entry.getKey(),
                        entry.getValue()))
                .collect(Collectors.joining("\n"));
        return authorMention + "\nCommands I Know:\n" + helpText;
    }
}
