package com.github.hehe3301;

import com.github.hehe3301.configs.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO Make this a unit test instead.
public class ConfigurationTestRunner
{
    public static void main(String[] args) throws IOException
    {
        Configuration testConfig = Configuration.from(String.join("\n",
                "com_prefix: '!'",
                "debug_enabled: true"
        ));
        System.out.println(testConfig.toYaml());

        Files.write(Paths.get("./testConfig.yaml"), testConfig.toYaml().getBytes());

        Configuration testConfig2 = Configuration.from(new FileInputStream("./testConfig.yaml"));
        System.out.println((testConfig2.bot_token == null)
                ? "default token is null"
                : "default token is not null");
    }
}
