package com.github.bigdata.examples.messagesender.file.process;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileReadException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Cannot read csv file from '%s'.";

    CsvFileReadException(String filename, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, filename), cause);
    }
}
