package com.github.bigdata.examples.messagesender.configuration;

/**
 * @author Mateusz Długosz
 */
public final class HoconConfigurationReadException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Cannot read configuration from file '%s'.";

    HoconConfigurationReadException(String filename, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, filename), cause);
    }
}
