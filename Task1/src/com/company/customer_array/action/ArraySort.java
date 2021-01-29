package com.company.customer_array.action;

import com.company.customer_array.exception.ArrayException;
import com.company.customer_array.entity.CustomerArray;

public class ArraySort {
    public CustomerArray selectionSort(CustomerArray array) throws ArrayException {
        ArrayChanging arrayChanging = new ArrayChanging();
        int length = array.length();
        for(int j = 0; j < length; j++) {
            int min = array.getElement(j);
            int minPosition = j;
            for (int i = j + 1; i < length; i++) {
                if (array.getElement(i) < min) {
                    min = array.getElement(i);
                    minPosition = i;
                }
            }
            array = arrayChanging.swapTwoElements(array, j, minPosition);
        }
        return array;
    }
    public CustomerArray bubbleSort(CustomerArray array) throws ArrayException {
        ArrayChanging arrayChanging = new ArrayChanging();
        int length = array.length();
        for(int i = 0; i < length; i++) {
            for(int j = 1; j < length; j++) {
                if(array.getElement(j) < array.getElement(j - 1)) {
                    arrayChanging.swapTwoElements(array, j, j - 1);
                }
            }
        }
        return array;
    }
    public CustomerArray insertionSort(CustomerArray array) throws ArrayException {
        int length = array.length();
        for(int i = 1; i < length; i++) {
            int current = array.getElement(i);
            int j = i;
            while(j > 0 && current < array.getElement(j - 1)) {
                int element = array.getElement(j - 1);
                array.setElement(j, element);
                j--;
            }
            array.setElement(j, current);
        }
        return array;
    }
}
