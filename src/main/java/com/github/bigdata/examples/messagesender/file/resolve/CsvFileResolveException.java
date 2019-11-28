package com.github.bigdata.examples.messagesender.file.resolve;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileResolveException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Cannot resolve files from path '%s'.";

    CsvFileResolveException(String path, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, path), cause);
    }
}
