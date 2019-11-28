package com.github.bigdata.examples.messagesender.file.process;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileRedisSendException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Cannot send csv file '%s' to redis.";

    CsvFileRedisSendException(CsvFile csvFile, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, csvFile.toString()), cause);
    }
}
