package com.github.bigdata.examples.messagesender;

import com.github.bigdata.examples.messagesender.configuration.ConfigKey;
import com.github.bigdata.examples.messagesender.configuration.HoconConfigFileReader;
import com.github.bigdata.examples.messagesender.file.process.*;
import com.github.bigdata.examples.messagesender.file.resolve.CsvFileResolver;
import com.github.bigdata.examples.messagesender.file.resolve.FileResolver;
import com.typesafe.config.Config;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Długosz
 */
public final class MessageSender {
    private static final Logger LOGGER = Logger.getLogger(MessageSender.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Application started.");
            LOGGER.info(String.format("Reading configuration from file '%s' started.", args[0]));
            Config config = getHoconConfigGileReader().read(args[0]);

            LOGGER.info("Initializing components.");
            FileResolver fileResolver = getFileResolver();
            FileReader<CsvFile> fileReader = getFileReader();
            FileSender<CsvFile> fileSender = getFileSender(config);

            LOGGER.info("Resolving files.");
            List<String> resolvedFiles = config.getStringList(ConfigKey.SOURCE_PATHS.getKeyName()).stream()
                    .map(fileResolver::resolve)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            LOGGER.info("Preparing sender.");
            fileSender.open();

            LOGGER.info("Sending files.");
            resolvedFiles.forEach(resolvedFile -> {
                LOGGER.info(String.format("Sending file '%s'.", resolvedFile));
                fileSender.send(fileReader.read(resolvedFile));
            });

            LOGGER.info("Closing sender.");
            fileSender.close();

            LOGGER.info("Application ended.");
        } catch (Exception e) {
            throw new MessageSenderException(e);
        }
    }

    private static HoconConfigFileReader getHoconConfigGileReader() {
        return new HoconConfigFileReader();
    }

    private static FileResolver getFileResolver() {
        return new CsvFileResolver();
    }

    private static FileReader<CsvFile> getFileReader() {
        return new CsvFileReader(",");
    }

    private static FileSender<CsvFile> getFileSender(Config config) {
        return new CsvFileRedisSender(
                config.getString(ConfigKey.SENDER_REDIS_HOSTNAME.getKeyName()),
                config.getInt(ConfigKey.SENDER_REDIS_PORT.getKeyName()),
                config.getString(ConfigKey.SENDER_REDIS_PASSWORD.getKeyName()),
                new CsvFileToJsonPreparer());
    }
}
