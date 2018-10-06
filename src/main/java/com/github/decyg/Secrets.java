package com.github.decyg;

public class  Secrets {
    private static Secrets ourInstance = new Secrets();

    public static Secrets getInstance() {
        return ourInstance;
    }

    private Secrets() {
    }

    public static const String BOT_TOKEN = "SOME_TOKEN"
}
