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

    public static String BOT_TOKEN = "NDkzMTE4MTk3NjM4MjM0MTEz.Dpo_kg.2Jh99MbP2B8DUTNMqqQ-Zh0TJhU";
}
