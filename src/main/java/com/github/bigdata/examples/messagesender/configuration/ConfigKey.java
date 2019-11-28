package com.github.bigdata.examples.messagesender.configuration;

/**
 * @author Mateusz Długosz
 */
public enum ConfigKey {
    SOURCE_PATHS("source-paths"),
    SENDER_REDIS_HOSTNAME("sender.redis.hostname"),
    SENDER_REDIS_PORT("sender.redis.port"),
    SENDER_REDIS_PASSWORD("sender.redis.password");

    private final String keyName;

    ConfigKey(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
}
