package com.github.hehe3301;

import com.github.hehe3301.configs.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO Make this a unit test instead.
public class ConfigurationTestRunner
{
    public static void main(String[] args) throws IOException
    {
        Configuration testConfig = Configuration.from(String.join("\n",
                "bot_token: Foobar",
                "com_prefix: '!'",
                "debug_enabled: true"
        ));
        System.out.println(testConfig.toYaml());

        Files.write(Paths.get("./secrets.yaml"), testConfig.toYaml().getBytes());
    }
}
