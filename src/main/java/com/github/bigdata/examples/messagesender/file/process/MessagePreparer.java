package com.github.bigdata.examples.messagesender.file.process;

import java.util.List;

/**
 * @author Mateusz Długosz
 */
public interface MessagePreparer<T extends FileFormat> {
    List<String> prepare(T file);
}
