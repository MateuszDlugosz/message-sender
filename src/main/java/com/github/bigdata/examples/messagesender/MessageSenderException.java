package com.github.bigdata.examples.messagesender;

/**
 * @author Mateusz Długosz
 */
public final class MessageSenderException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Message sender error.";

    MessageSenderException(Throwable cause) {
        super(MESSAGE_TEMPLATE, cause);
    }
}
