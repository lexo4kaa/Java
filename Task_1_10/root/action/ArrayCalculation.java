package root.action;

import root.entity.CustomerArray;
import root.exception.ArrayException;

public class ArrayCalculation {
    public int minElement(CustomerArray arr) throws ArrayException {
        int min = arr.getElement(0);
        int len = arr.length();
        for (int i = 1; i < len; i++) {
            if(arr.getElement(i) < min) {
                min = arr.getElement(i);
            }
        }
        return min;
    }
    public int maxElement(CustomerArray arr) throws ArrayException {
        int max = arr.getElement(0);
        int len = arr.length();
        for (int i = 1; i < len; i++) {
            if(arr.getElement(i) > max) {
                max = arr.getElement(i);
            }
        }
        return max;
    }
    public int sumOfElements(CustomerArray arr) throws ArrayException {
        int sum = 0;
        int len = arr.length();
        for (int i = 0; i < len; i++) {
            sum += arr.getElement(i);
        }
        return sum;
    }
    public double avgOfElements(CustomerArray arr) throws ArrayException {
        return (double) sumOfElements(arr)/(double)arr.length();
    }
    public int countOfPositiveElements(CustomerArray arr) throws ArrayException {
        int count = 0;
        int len = arr.length();
        for (int i = 0; i < len; i++) {
            if(arr.getElement(i) > 0) {
                count++;
            }
        }
        return count;
    }
    public int countOfNegativeElements(CustomerArray arr) throws ArrayException {
        int count = 0;
        int len = arr.length();
        for (int i = 0; i < len; i++) {
            if(arr.getElement(i) < 0) {
                count++;
            }
        }
        return count;
    }

}
