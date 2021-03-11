package com.company.multithreading.reader;

import com.company.multithreading.exception.CustomerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerFileReader {
    public static Logger logger = LogManager.getLogger();
    public static List<String> fileReading(String path) throws CustomerException {
        if (path == null) {
            throw new CustomerException("Path is null");
        }
        Path filePath = Paths.get(path);
        List<String> lines;
        try (Stream<String> streamLines = Files.lines(filePath)) {
            lines = streamLines.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("File in " + path + " not found", e);
            throw new CustomerException("File in " + path + " not found", e);
        }
        return lines;
    }
}
