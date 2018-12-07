package com.github.hehe3301.time_handler;

import com.github.hehe3301.conditional_print.CP;
import com.github.hehe3301.configs.Configuration;

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
public class TimeHandler
{
    private Configuration config;
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
        CP.cLog(config.debug_enabled, "Calling now on: " + p_time_zone + "\n");
        //If we have an alias, un-alias
        String time_zone = unAlias(p_time_zone);
        CP.cLog(config.debug_enabled, "After unAlias: " + time_zone + "\n");

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
            return "Invalid time zone: " + p_time_zone;
        }

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(time_zone));
        //Time in GMT

        //Local time zone
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        SimpleDateFormat justTime = new SimpleDateFormat("HHmm");
        Date time;
        try
        {
            time = dateFormatLocal.parse(dateFormatGmt.format(new Date()));
        } catch (ParseException e)
        {
            e.printStackTrace();
            return "!PARSE ERROR!";
        }

        return "The time is now: " + justTime.format(time) + " " + p_time_zone;
    }

    public TimeHandler(Configuration config)
    {
        this.config = config;
        loadAliases();
    }

    public String getAliases()
    {
        String printString = "Aliases I know:";
        for (String key : aliasMap.keySet())
        {
            printString = printString + "\n" + key + "=\t Standard Time: " + aliasMap.get(key).get(0) + ",\t Daylight Savings Time: " + aliasMap.get(key).get(1);
        }
        return printString;
    }

    public String dumpZones()
    {
        return "Just go look at: https://github.com/hehe3301/Discord-Time-Bot";
    }

    /**
     * This function loads the aliases from the alias file in Settings
     */
    private void loadAliases()
    {
        String csvFile = config.alias_file;
        String line = "";
        String cvsSplitBy = ",";

        String basePath = new File("").getAbsolutePath();//where am i?
        CP.cLog(config.debug_enabled, basePath+"\n");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {

            while ((line = br.readLine()) != null)
            {

                // use comma as separator
                String[] alias_line = line.split(cvsSplitBy);
                String alias = alias_line[0].replaceAll("\"", "").toUpperCase();
                aliasMap.put(alias, new ArrayList());
                aliasMap.get(alias).add(0, alias_line[1].replaceAll("\"", "").toUpperCase());
                aliasMap.get(alias).add(1, alias_line[2].replaceAll("\"", "").toUpperCase());
                CP.cLog(config.debug_enabled, String.format("Adding %s as %s, %s \n",
                        alias,
                        aliasMap.get(alias).get(0),
                        aliasMap.get(alias).get(1)));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        CP.cLog(config.debug_enabled, "Aliases loaded: " + aliasMap.size() + "\n");

    }

    /**
     * This function takes in a string and checks to see if that is a known alias and returns the unaliased string.
     * @param thing - the string to unalias
     * @return - the unaliased string or original if none found.
     */
    private String unAlias(String thing)
    {
        CP.cLog(config.debug_enabled, "Checking for alias: " + thing + "\n");
        String tz = thing;

        //If we have an alias for this
        if (aliasMap.get(tz) == null)
        {
            return tz;
        } else
        {
            //If it is daylight savings time
            if (TimeZone.getDefault().inDaylightTime(new Date()))
            {
                return unAlias(aliasMap.get(tz).get(1));
            } else
            {
                return unAlias(aliasMap.get(tz).get(0));
            }
        }

    }


    public String cleanTime(List<String> p_time_list)
    {
        String time_list = "Converted time(s):\n";
        for (Iterator<String> i = p_time_list.iterator(); i.hasNext();) {
            String item = i.next();
            time_list = time_list + cleanTime(item) + "\n";
        }
        return time_list;
    }


    /**
     * This function just returns a string of all known aliases
     * @return
     */
    private String cleanTime(String p_time)
    {
        CP.cLog(Settings.debug_enabled, "Cleaning time:"+p_time+"\n");
        if( p_time.contains(":") )
        {
            return cleanTime(p_time.replace(":", ""));
        }
        else if ( p_time.toLowerCase().contains("am") )
        {
            return cleanTime(p_time.toLowerCase().replace("am", ""));
        }
        else if ( p_time.toLowerCase().contains("pm") )
        {
            String tmp = p_time.toLowerCase().replace("pm", "");
            if(tmp.length()<3)
            {
                tmp = Integer.toString(Integer.parseInt(tmp)+12) + "00";
            }
            else
            {
                tmp= Integer.toString(Integer.parseInt(tmp.substring(0, tmp.length()-2))+12)+tmp.substring(tmp.length()-2, tmp.length()) ;
            }
            return cleanTime(tmp);
        }
        else if (p_time.length()<3)
        {
            return cleanTime(p_time+"00");
        }
        else if (p_time.length()<4)
        {
            return cleanTime("0"+p_time);
        }
        return p_time;
    }

    public String translateTime(String p_time, String p_source, String p_dest)
    {
        String src_time_zone = unAlias(p_source);
        String dest_time_zone = unAlias(p_dest);
        String clean_time=cleanTime(p_time);

        CP.cLog(Settings.debug_enabled, "\nTranslating time: "+clean_time+" from "+src_time_zone+" to "+dest_time_zone+"\n");

        //if the time zone is valid
        if(Arrays.asList(TimeZone.getAvailableIDs()).contains(src_time_zone) &&
                Arrays.asList(TimeZone.getAvailableIDs()).contains(dest_time_zone))
        {
            //do nothing
        }
        else if(Arrays.asList(TimeZone.getAvailableIDs()).contains(src_time_zone.toUpperCase()))
        //else if the uppercase version of the TZ is in the list
        {
            src_time_zone=src_time_zone.toUpperCase();

            if(Arrays.asList(TimeZone.getAvailableIDs()).contains(dest_time_zone.toUpperCase()))
            //if the uppercase version of the TZ is in the list
            {
                dest_time_zone=dest_time_zone.toUpperCase();
            }
        }
        else if(Arrays.asList(TimeZone.getAvailableIDs()).contains(dest_time_zone.toUpperCase()))
        //else if the uppercase version of the TZ is in the list
        {
            dest_time_zone=dest_time_zone.toUpperCase();

            if(Arrays.asList(TimeZone.getAvailableIDs()).contains(src_time_zone.toUpperCase()))
            //if the uppercase version of the TZ is in the list
            {
                src_time_zone=src_time_zone.toUpperCase();
            }
        }
        else
        //this must be an invalid time zone
        {
            return "Invalid time zone: " + p_source+" or "+ p_dest;
        }

        Calendar src_time = Calendar.getInstance(TimeZone.getTimeZone(src_time_zone));

        src_time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(clean_time.substring(0,2)));
        src_time.set(Calendar.MINUTE, Integer.parseInt(clean_time.substring(2,4)));

        Calendar dest_time = new GregorianCalendar(TimeZone.getTimeZone(dest_time_zone));
        dest_time.setTimeInMillis(src_time.getTimeInMillis());

        String string_time = Integer.toString(dest_time.get(Calendar.HOUR_OF_DAY)) + Integer.toString(dest_time.get(Calendar.MINUTE));

        return p_time+" in " +p_source+" is "+cleanTime(string_time)+" in "+p_dest;
    }

    public String translateTime(String p_time, String p_source)
    {
        return translateTime(p_time, p_source, "UTC");
    }
}
