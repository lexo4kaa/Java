package com.company.customer_array.action;

import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArrayParser {
    static Logger logger = LogManager.getLogger();
    public static CustomerArray arrayParser(List<String> arrayList) throws ArrayException {
        final String SEPARATORS = "[ ,;@]";
        for(String line : arrayList) {
            String[] splitLine = line.split(SEPARATORS);
            int[] resultArray = new int[splitLine.length];
            boolean flag = true;
            for(int i = 0; i < splitLine.length; i++) {
                String current = splitLine[i];
                try {
                    int num = Integer.parseInt(current);
                    resultArray[i] = num;
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
        return new CustomerArray(1);
    }
}