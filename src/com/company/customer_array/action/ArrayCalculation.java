package com.company.customer_array.action;

import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;

public class ArrayCalculation {
    public int minElement(CustomerArray array) throws ArrayException {
        int min = array.getElement(0);
        int len = array.length();
        for (int i = 1; i < len; i++) {
            if(array.getElement(i) < min) {
                min = array.getElement(i);
            }
        }
        return min;
    }
    public int maxElement(CustomerArray array) throws ArrayException {
        int max = array.getElement(0);
        int len = array.length();
        for (int i = 1; i < len; i++) {
            if(array.getElement(i) > max) {
                max = array.getElement(i);
            }
        }
        return max;
    }
    public int sumOfElements(CustomerArray array) throws ArrayException {
        int sum = 0;
        int len = array.length();
        for (int i = 0; i < len; i++) {
            sum += array.getElement(i);
        }
        return sum;
    }
    public double avgOfElements(CustomerArray array) throws ArrayException {
        return (double)sumOfElements(array)/array.length();
    }
    public int countOfPositiveElements(CustomerArray array) throws ArrayException {
        int count = 0;
        int len = array.length();
        for (int i = 0; i < len; i++) {
            if(array.getElement(i) > 0) {
                count++;
            }
        }
        return count;
    }
    public int countOfNegativeElements(CustomerArray array) throws ArrayException {
        int count = 0;
        int len = array.length();
        for (int i = 0; i < len; i++) {
            if(array.getElement(i) < 0) {
                count++;
            }
        }
        return count;
    }
}
