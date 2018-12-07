package com.github.hehe3301.configs;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class Configuration
{
    public String bot_token = null;
    public String com_prefix = ">>";
    public boolean debug_enabled = true;
    public String alias_file = "src/main/resources/aliases.csv";

    public static Configuration from(InputStream in)
    {
        Yaml yaml = new Yaml();
        return yaml.loadAs(in, Configuration.class);
    }

    public static Configuration from(String in)
    {
        Yaml yaml = new Yaml();
        return yaml.loadAs(in, Configuration.class);
    }

    public static Configuration defaults()
    {
        return new Configuration();
    }

    public String toYaml()
    {
        Yaml yaml = new Yaml();
        return yaml.dumpAsMap(this);
    }
}
