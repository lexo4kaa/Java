package com.company.customer_array.action;

import com.company.customer_array.exception.ArrayException;
import com.company.customer_array.entity.CustomerArray;

public class ArraySort {
    public CustomerArray selectionSort(CustomerArray array) throws ArrayException {
        ArrayChanging a = new ArrayChanging();
        int len = array.length();
        for(int j = 0; j < len; j++) {
            int min = array.getElement(j);
            int pos = j;
            for (int i = j + 1; i < len; i++) {
                if (array.getElement(i) < min) {
                    min = array.getElement(i);
                    pos = i;
                }
            }
            array = a.swapTwoElements(array, j, pos);
        }
        return array;
    }
    public CustomerArray bubbleSort(CustomerArray array) throws ArrayException {
        ArrayChanging a = new ArrayChanging();
        int len = array.length();
        for(int i = 0; i < len; i++) {
            for(int j = 1; j < len; j++) {
                if(array.getElement(j) < array.getElement(j - 1)) {
                    a.swapTwoElements(array, j, j - 1);
                }
            }
        }
        return array;
    }
    public CustomerArray insertionSort(CustomerArray array) throws ArrayException {
        int len = array.length();
        for(int i = 1; i < len; i++) {
            int current = array.getElement(i);
            int j = i;
            while(j > 0 && current < array.getElement(j - 1)) {
                int elem = array.getElement(j - 1);
                array.setElement(j, elem);
                j--;
            }
            array.setElement(j, current);
        }
        return array;
    }
}
