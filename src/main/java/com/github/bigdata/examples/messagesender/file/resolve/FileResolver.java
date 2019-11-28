package com.github.bigdata.examples.messagesender.file.resolve;

import java.util.List;

/**
 * @author Mateusz Długosz
 */
public interface FileResolver {
    List<String> resolve(String path);
}
