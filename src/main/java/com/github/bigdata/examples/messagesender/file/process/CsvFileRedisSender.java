package com.github.bigdata.examples.messagesender.file.process;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileRedisSender implements FileSender<CsvFile> {
    private final String redisHostname;
    private final int redisPort;
    private final String redisPassword;
    private final CsvFileToJsonPreparer csvFileToJsonPreparer;
    private Jedis jedis;

    public CsvFileRedisSender(
            String redisHostname,
            int redisPort,
            String redisPassword,
            CsvFileToJsonPreparer csvFileToJsonPreparer) {
        this.redisHostname = redisHostname;
        this.redisPort = redisPort;
        this.redisPassword = redisPassword;
        this.csvFileToJsonPreparer = csvFileToJsonPreparer;
    }

    @Override
    public void open() {
        //jedis = new Jedis(redisHostname, redisPort);
        //jedis.auth(redisPassword);
    }

    @Override
    public void send(CsvFile file) {
        try {
            List<String> messages = csvFileToJsonPreparer.prepare(file);
            String key = createRedisKey(file);

            messages.forEach(message -> jedis.set(key, message));
        } catch (Exception e) {
            throw new CsvFileRedisSendException(file, e);
        }
    }

    private String createRedisKey(CsvFile csvFile) {
        return Paths.get(csvFile.getFilename()).toFile().getName();
    }

    @Override
    public void close() {
        //jedis.close();
    }
}
