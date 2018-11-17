package com.github.hehe3301.bot;

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
    private interface Command
            extends BiConsumer<MessageReceivedEvent, List<String>> {}
    private Map<String, Command> commandMap;
    private Map<String, String> helpMap;
    private String prefix;

    /**
     * A utility object that parses and executes chat commands.
     * @param prefix The prefix for commands, as in !command.
     * @param timeHandler The TimeHandler utility object.
     * @param debugEnabled Whether or not to log debug messages.
     */
    public CommandHandler(
            String prefix,
            TimeHandler timeHandler,
            boolean debugEnabled)
    {
        this.helpMap = new HashMap<>();
        this.commandMap = new HashMap<>();
        this.prefix = prefix;

        // Command: now
        helpMap.put("now", String.format(
                "%s prints the current time in all supplied timezones," +
                        " or in UTC if none are given.",
                prefix + "now"));
        commandMap.put("now", (event, args) -> {
            CP.cLog(debugEnabled, String.format(
                    "User: %s instructed me to get current time.\n",
                    event.getAuthor().getName()));
            String reply = nowString(
                    event.getAuthor().mention(),
                    args,
                    timeHandler);
            event.getChannel().sendMessage(reply);
        });

        // Command: help
        helpMap.put("help", String.format(
                "%s prints this help message.",
                prefix + "help"));
        commandMap.put("help", (event, args) -> {
            CP.cLog(debugEnabled, String.format(
                    "User: %s printed the help.\n",
                    event.getAuthor().getName()));
            String reply = helpString(
                    event.getAuthor().mention(),
                    helpMap);
            event.getChannel().sendMessage(reply);
        });

        // Command: alias
        helpMap.put("alias", String.format(
                "%1$s adds an alias for a timezone, and optionally its" +
                        " daylight savings version." +
                        " ex: %1$s Eastern EST EDT" +
                        " (UNIMPLEMENTED)",
                prefix + "alias"));
        commandMap.put("alias", (event, args) -> {
            //TODO implement
            String reply = event.getAuthor().mention() + "\n"
                    + "This feature has not been implemented yet!";
            event.getChannel().sendMessage(reply);
        });

        // Command: aliases
        helpMap.put("aliases", String.format(
                "%s prints the list of known aliases",
                prefix + "aliases"));
        commandMap.put("aliases", (event, args) -> {
            String reply = event.getAuthor().mention() + "\n"
                    + timeHandler.getAliases();
            event.getChannel().sendMessage(reply);
        });

        // Command: time
        // TODO: Consider renaming this one? -nupa
        helpMap.put("time", String.format(
                "%s translates a time from one timezone to another. " +
                        "ex: %1$s 7pm est utc (UNIMPLEMENTED)",
                prefix + "time"));
        commandMap.put("time", (event, args) -> {
            //TODO implement
            String reply = event.getAuthor().mention() + "\n"
                    + "This feature has not been implemented yet!";
            event.getChannel().sendMessage(reply);
        });

        // Command: zones
        helpMap.put("zones", String.format(
                "%s lists all the known time zones.",
                prefix + "zones"));
        commandMap.put("zones", (event, args) -> {
            String reply = event.getAuthor().mention() + "\n"
                    + timeHandler.dumpZones();
            event.getChannel().sendMessage(reply);
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
        if(!argArray[0].startsWith(prefix))
            return;

        // Extract the "command" part of the first arg out by ditching the amount of characters present in the prefix
        String commandStr = argArray[0].substring(prefix.length());

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        // Instead of delegating the work to a switch, automatically do it via calling the mapping if it exists
        if(!commandMap.containsKey(commandStr))
            return;

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
            TimeHandler timeHandler)
    {
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
            Map<String, String> helpMap)
    {
        String helpText = helpMap.entrySet().stream()
                .map(entry -> String.format("%s: %s",
                        entry.getKey(),
                        entry.getValue()))
                .collect(Collectors.joining("\n"));
        return authorMention + "\nCommands I Know:\n" + helpText;
    }
}
