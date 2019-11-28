package com.github.bigdata.examples.messagesender.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

/**
 * @author Mateusz Długosz
 */
public final class HoconConfigFileReader {
    public Config read(String filename) {
        try {
            return ConfigFactory.parseFile(new File(filename));
        } catch (Exception e) {
            throw new HoconConfigurationReadException(filename, e);
        }
    }
}
