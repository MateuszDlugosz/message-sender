package com.github.bigdata.examples.messagesender.file.process;

/**
 * @author Mateusz Długosz
 */
public interface FileSender<T extends FileFormat> {
    void open();
    void send(T file);
    void close();
}
