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

    public String now()
    {
        return now("UTC");
    }

    public String now(String p_time_zone)
    {
        //If we have an alias, un-alias
        String time_zone = unAlias(p_time_zone);

        if(!Arrays.asList(TimeZone.getAvailableIDs()).contains(time_zone) )
        {
            return "Invalid time zone: "+p_time_zone;
        }

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(time_zone.toUpperCase()));
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

    private void loadAliases()
    {
        String csvFile = Settings.alias_file;
        String line = "";
        String cvsSplitBy = ",";

        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath);

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

    private String unAlias(String thing)
    {
        CP.cLog(Settings.debug_enabled, "Checking for alias: "+ thing +"\n");
        String tz=thing.toUpperCase();

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

    public TimeHandler() {
        loadAliases();
    }

    public String getAliases()
    {
        String printString = "Aliases I know:";
        for (String key : aliasMap.keySet())
        {
            printString=printString+"\n"+key+"=\t Standard Time: "+aliasMap.get(key).get(0)+",\t Daylight Savings Time: "+aliasMap.get(key).get(1);
        }
        return printString;
    }

    public String dumpZones()
    {
        String printString = "";

        for (Iterator<String> i = Arrays.asList(TimeZone.getAvailableIDs()).iterator(); i.hasNext();)
        {
            String item = i.next();

            System.out.println(item);
            printString = printString+item+"\n";
        }

        return printString;
    }
}
