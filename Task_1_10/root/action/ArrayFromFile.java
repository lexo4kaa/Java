package root.action;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import root.entity.CustomerArray;
import root.exception.ArrayException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayFromFile {
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
                logger.log(Level.ERROR, "File not found", e);
                throw new ArrayException("File in " + path + " not found");
            }
        }
        else {
            logger.log(Level.ERROR, "Problems with file");
            throw new ArrayException("Problems with file");
        }
        return listOfArrays;
    }
    public static CustomerArray arraySelection(List<String> arrayList) throws ArrayException {
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