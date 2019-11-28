package com.github.bigdata.examples.messagesender.file.process;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Długosz
 */
public final class CsvFileReader implements FileReader<CsvFile> {
    private static final CsvFileRow EMPTY_ROW = new CsvFileRow(Collections.emptyList());

    private final String separator;

    public CsvFileReader(String separator) {
        this.separator = separator;
    }

    @Override
    public CsvFile read(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            if (lines.size() == 0) return new CsvFile(filename, EMPTY_ROW, Collections.emptyList());
            if (lines.size() == 1) return new CsvFile(filename, readHeaders(lines), Collections.emptyList());

            return new CsvFile(filename, readHeaders(lines), readRows(lines));
        } catch (Exception e) {
            throw new CsvFileReadException(filename, e);
        }
    }

    private CsvFileRow readHeaders(List<String> lines) {
        return readRow(lines.get(0));
    }

    private List<CsvFileRow> readRows(List<String> lines) {
        return lines.subList(1, lines.size())
                .stream()
                .map(this::readRow)
                .collect(Collectors.toList());
    }

    private CsvFileRow readRow(String line) {
        return new CsvFileRow(Arrays.stream(line.split(separator)).collect(Collectors.toList()));
    }
}
