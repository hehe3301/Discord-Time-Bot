package com.github.hehe3301.configs;

/**
 * Created by hehe3 on 10/7/2018.
 */
public class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
    }

    public static String com_prefix = ">>";
    public static Boolean debug_enabled = Boolean.TRUE;

}
