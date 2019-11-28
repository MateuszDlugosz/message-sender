package com.github.bigdata.examples.messagesender.file.process;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileToJsonPrepareException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Cannot prepare json messages from csv file '%s'.";

    CsvFileToJsonPrepareException(CsvFile csvFile, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, csvFile.toString()), cause);
    }
}
