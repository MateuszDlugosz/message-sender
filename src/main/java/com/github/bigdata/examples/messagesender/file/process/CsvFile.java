package com.github.bigdata.examples.messagesender.file.process;

import com.github.bigdata.examples.messagesender.file.process.FileFormat;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * @author Mateusz Długosz
 */
public final class CsvFile implements FileFormat {
    private final String filename;
    private final CsvFileRow headers;
    private final List<CsvFileRow> rows;

    public CsvFile(String filename, CsvFileRow headers, List<CsvFileRow> rows) {
        this.filename = filename;
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    public CsvFileRow getHeaders() {
        return headers;
    }

    public List<CsvFileRow> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("filename", filename)
                .add("headers", headers)
                .add("rows", rows)
                .toString();
    }
}
