package com.company.composite_chain.reader;

import com.company.composite_chain.exception.CustomerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerFileReader {
    private static final String TEXT_DELIMITER = "\n";
    public static Logger logger = LogManager.getLogger();
    public static String fileReading(String path) throws CustomerException {
        if (path == null) {
            throw new CustomerException("Path is null");
        }
        Path filePath = Paths.get(path);
        StringBuilder text = new StringBuilder();
        try (Stream<String> streamLines = Files.lines(filePath)) {
            text.append(streamLines.collect(Collectors.joining(TEXT_DELIMITER)));
            text.append("\n");
        } catch (IOException e) {
            logger.error("File in " + path + " not found", e);
            throw new CustomerException("File in " + path + " not found", e);
        }
        return text.toString();
    }
}
