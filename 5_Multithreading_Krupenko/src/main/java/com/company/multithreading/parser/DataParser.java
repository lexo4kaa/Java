package com.company.multithreading.parser;

import com.company.multithreading.exception.CustomerException;

import java.util.ArrayList;
import java.util.List;

import static com.company.multithreading.reader.CustomerFileReader.logger;

public class DataParser {
    private static final String SEPARATORS = "[ ,;]";
    public List<Integer> parse(String line) throws CustomerException {
        String[] elements = line.split(SEPARATORS);
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < elements.length - 1; i++){
            Integer number = Integer.parseInt(elements[i]);
            numbers.add(number);
        }
        String targetString = elements[elements.length - 1].toUpperCase();
        int targetInt;
        if(targetString.equals("LOADING")) {
            targetInt = 1;
        } else if(targetString.equals("UNLOADING")) {
            targetInt = 2;
        } else if(targetString.equals("LOADING_UNLOADING")) {
            targetInt = 0;
        } else {
            logger.error("Ship target incorrect: " + targetString);
            throw new CustomerException("Ship target incorrect: " + targetString);
        }
        numbers.add(targetInt);
        return numbers;
    }
}
