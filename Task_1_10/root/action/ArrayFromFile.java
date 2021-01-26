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
import java.util.Collections;
import java.util.Scanner;

public class ArrayFromFile {
    static Logger logger = LogManager.getLogger();
    public List<List<String>> arrayReading(String path) throws ArrayException {
        final String SEPARATORS = "[ ,;@]";
        List<List<String>> listOfArrays = new ArrayList<>();
        File f = new File(path);
        if (f.exists() && f.isFile() && f.canRead()) {
            try (Scanner input = new Scanner(f)) {
                while (input.hasNextLine()) {
                    List<String> array = new ArrayList<>();
                    String line = input.nextLine();
                    String[] splitLine = line.split(SEPARATORS);
                    Collections.addAll(array, splitLine);
                    listOfArrays.add(array);
                }
            } catch (FileNotFoundException e) {
                logger.log(Level.ERROR, "File not found", e);
                throw new ArrayException();
            }
        }
        return listOfArrays;
    }
    public CustomerArray arraySelection(List<List<String>> arrayList) throws ArrayException {
        for(List<String> array : arrayList) {
            int[] resultArray = new int[array.size()];
            boolean flag = true;
            for(int i = 0; i < array.size(); i++) {
                String current = array.get(i);
                try {
                    int num = Integer.parseInt(current);
                    resultArray[i] = num;
                } catch (NumberFormatException e) {
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