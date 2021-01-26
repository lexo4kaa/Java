package root.action;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import root.entity.CustomerArray;
import root.exception.ArrayException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ArrayFromFile {
    static Logger logger = LogManager.getLogger();
    /**
     * A function that reads a file containing arrays
     *
     * @param path - path to text file
     * @return listOfArray - a list, each element of which is an array, stored as a list
     */
    public ArrayList<ArrayList<String>> arrayReading(String path) throws ArrayException {
        FileReader fr;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File not found", e);
            throw new ArrayException("File not found");
        }
        Scanner scan = new Scanner(fr);
        final String SEPARATORS = "[ ,;@]";
        ArrayList<ArrayList<String>> listOfArrays = new ArrayList<>();
        while (scan.hasNextLine()) {
            ArrayList<String> array = new ArrayList<>();
            String line = scan.nextLine();
            String[] splitLine = line.split(SEPARATORS);
            for (String s : splitLine) {
                array.add(s);
            }
            listOfArrays.add(array);
        }
        try {
            fr.close();
        } catch (IOException e) {
            logger.log(Level.ERROR, "Сlosing problems ", e);
            throw new ArrayException("Сlosing problems");
        }
        return listOfArrays;
    }
    /**
     * A function that selects an integer array
     *
     * @param arrayList - a list, each element of which is an array, stored as a list
     * @return arr - an integer array. if there is no integer array then 0
     */
    public CustomerArray arraySelection(ArrayList<ArrayList<String>> arrayList) throws ArrayException {
        for(int j = 0; j < arrayList.size(); j++) {
            ArrayList<String> array = arrayList.get(j);
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
                CustomerArray arr = new CustomerArray(resultArray);
                return arr;
            }
        }
        return new CustomerArray(1);
    }
}