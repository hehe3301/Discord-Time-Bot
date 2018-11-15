package com.github.hehe3301.time_handler;
import com.github.hehe3301.conditional_print.CP;
import com.github.hehe3301.configs.Settings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hehe3301 on 10/7/2018.
 */
public class TimeHandler{

    private Map<String, ArrayList<String>> aliasMap = new HashMap<>();

    /**
     * Default parametered now that just returns now at UTC
     * @return - current time at UTC
     */
    public String now()
    {
        return now("UTC");
    }

    /**
     * This function gets the current time at a specified times zone.
     * @param p_time_zone - a time zone.
     * @return  - current time at p_time_zone
     */
    public String now(String p_time_zone)
    {
        CP.cLog(Settings.debug_enabled, "Calling now on: "+p_time_zone+"\n");
        //If we have an alias, un-alias
        String time_zone = unAlias(p_time_zone);

        //if the time zone is valid
        if(Arrays.asList(TimeZone.getAvailableIDs()).contains(time_zone) )
        {
            //do nothing
        }
        else if(Arrays.asList(TimeZone.getAvailableIDs()).contains(time_zone.toUpperCase()))
            //else if the uppercase version of the TZ is in the list
        {
            time_zone=time_zone.toUpperCase();
        }
        else
            //this must be an invalid time zone
        {
            return "Invalid time zone: "+p_time_zone;
        }

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(time_zone));
        //Time in GMT

        //Local time zone
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        SimpleDateFormat justTime= new SimpleDateFormat("HHmm");
        Date time;
        try
        {
             time = dateFormatLocal.parse(dateFormatGmt.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return "!PARSE ERROR!";
        }

        return "The time is now: "+justTime.format(time) +" "+p_time_zone;
    }

    /**
     * This function loads the aliases from the alias file in Settings
     */
    private void loadAliases()
    {
        String csvFile = Settings.alias_file;
        String line = "";
        String cvsSplitBy = ",";

        String basePath = new File("").getAbsolutePath();//where am i?
        CP.cLog(Settings.debug_enabled, basePath+"\n");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] alias_line = line.split(cvsSplitBy);
                String alias = alias_line[0].replaceAll("\"","").toUpperCase();
                aliasMap.put(alias, new ArrayList() );
                aliasMap.get(alias).add(0, alias_line[1].replaceAll("\"","").toUpperCase());
                aliasMap.get(alias).add(1, alias_line[2].replaceAll("\"","").toUpperCase());
                CP.cLog(Settings.debug_enabled, "Adding: " + alias + " as " + aliasMap.get(alias).get(0) + ", " + aliasMap.get(alias).get(1) + "\n" );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CP.cLog(Settings.debug_enabled, "Aliases loaded: "+ aliasMap.size() +"\n");

    }

    /**
     * This function takes in a string and checks to see if that is a known alias and returns the unaliased string.
     * @param thing - the string to unalias
     * @return - the unaliased string or original if none found.
     */
    private String unAlias(String thing)
    {
        CP.cLog(Settings.debug_enabled, "Checking for alias: "+ thing +"\n");
        String tz=thing;

        //If we have an alias for this
        if(aliasMap.get(tz) == null)
        {
            return tz;
        }
        else
        {
            //If it is daylight savings time
            if(TimeZone.getDefault().inDaylightTime( new Date() ))
            {
                return unAlias(aliasMap.get(tz).get(1));
            }
            else
            {
                return unAlias(aliasMap.get(tz).get(0));
            }
        }

    }

    /**
     * Public constructor, just needs to load aliases.
     */
    public TimeHandler() {
        loadAliases();
    }

    /**
     * This function just returns a string of all known aliases
     * @return
     */
    public String getAliases()
    {
        String printString = "Aliases I know:";
        for (String key : aliasMap.keySet())
        {
            printString=printString+"\n"+key+"=\t Standard Time: "+aliasMap.get(key).get(0)+",\t Daylight Savings Time: "+aliasMap.get(key).get(1);
        }
        return printString;
    }

    /**
     * This function just returns the link to the list of usable time zones as defined by java.
     * @return -  the link to the readme.
     */
    public String dumpZones()
    {
        return "Just go look at: https://github.com/hehe3301/Discord-Time-Bot/blob/master/README.md";
    }
}
