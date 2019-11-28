package com.github.bigdata.examples.messagesender.file.resolve;

import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileResolver implements FileResolver {
    private static final Logger LOGGER = Logger.getLogger(CsvFileResolver.class);
    private static final String CSV_FILE_EXTENSION = ".csv";

    @Override
    public List<String> resolve(String path) {
        try {
            try (Stream<Path> paths = Files.walk(Paths.get(path))) {
                LOGGER.info("Resolving files from path '%s'.");
                List<String> resolvedFiles = paths
                        .filter(Files::isRegularFile)
                        .filter(filePath -> filePath.toString().endsWith(CSV_FILE_EXTENSION))
                        .map(Path::toString)
                        .collect(Collectors.toList());
                LOGGER.info(String.format("Resolved files [%s].", resolvedFiles));

                return resolvedFiles;
            }
        } catch (Exception e) {
            throw new CsvFileResolveException(path, e);
        }
    }
}
