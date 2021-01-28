package com.company.customer_array.action;

import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;

import java.util.List;

import static com.company.customer_array.action.ArrayReading.logger;

public class ArrayParser {
    public static CustomerArray arrayParser(List<String> arrayList) throws ArrayException {
        final String SEPARATORS = "[ ,;@]";
        for(String line : arrayList) {
            String[] splitLine = line.split(SEPARATORS);
            int[] resultArray = new int[splitLine.length];
            boolean flag = true;
            for(int i = 0; i < splitLine.length; i++) {
                String current = splitLine[i];
                try {
                    resultArray[i] = Integer.parseInt(current);
                } catch (NumberFormatException e) {
                    logger.error(current + " at position " + i + " is not an integer number");
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return new CustomerArray(resultArray);
            }
        }
        logger.error("There are no integer array in " + arrayList);
        throw new ArrayException("There are no integer array in " + arrayList);
    }
}