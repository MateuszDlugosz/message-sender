package com.github.bigdata.examples.messagesender.file.process;

/**
 * @author Mateusz Długosz
 */
public interface FileReader<T extends FileFormat> {
    T read(String filename);
}
