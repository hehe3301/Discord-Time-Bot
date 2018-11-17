package com.github.hehe3301.configs;

public class Secrets
{
    private static Secrets ourInstance = new Secrets();

    public static Secrets getInstance()
    {
        return ourInstance;
    }

    private Secrets()
    {
    }

    public static String BOT_TOKEN = "TOKEN HERE";
}
