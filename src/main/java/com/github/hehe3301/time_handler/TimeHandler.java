package com.github.hehe3301.time_handler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by hehe3 on 10/7/2018.
 */
public class TimeHandler {
    public static String now()
    {
        return now("UTC");
    }

    public static String now(String time_zone)
    {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(time_zone));
        //Time in GMT

        //Local time zone
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

        SimpleDateFormat justTime= new SimpleDateFormat("HHmm");
        Date time;
        try {
             time = dateFormatLocal.parse(dateFormatGmt.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return "!PARSE ERROR!";
        }

        return "The time is now: "+justTime.format(time) +" "+time_zone;


    }
}
