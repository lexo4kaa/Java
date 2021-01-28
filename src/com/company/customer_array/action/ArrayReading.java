package com.company.customer_array.action;

import com.company.customer_array.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayReading {
    static Logger logger = LogManager.getLogger();
    public static List<String> arrayReading(String path) throws ArrayException {
        List<String> listOfArrays = new ArrayList<>();
        File f = new File(path);
        if (f.exists() && f.isFile() && f.canRead()) {
            try (Scanner input = new Scanner(f)) {
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    listOfArrays.add(line);
                }
            } catch (FileNotFoundException e) {
                logger.error("File not found", e);
                throw new ArrayException("File in " + path + " not found");
            }
        } else {
            logger.error("Problems with file");
            throw new ArrayException("Problems with file");
        }
        return listOfArrays;
    }
}