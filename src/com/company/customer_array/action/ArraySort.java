package com.company.customer_array.action;

import com.company.customer_array.exception.ArrayException;
import com.company.customer_array.entity.CustomerArray;

public class ArraySort {
    public CustomerArray selectionSort(CustomerArray arr) throws ArrayException {
        ArrayChanging a = new ArrayChanging();
        int len = arr.length();
        for(int j = 0; j < len; j++) {
            int min = arr.getElement(j);
            int pos = j;
            for (int i = j + 1; i < len; i++) {
                if (arr.getElement(i) < min) {
                    min = arr.getElement(i);
                    pos = i;
                }
            }
            arr = a.swapTwoElements(arr, j, pos);
        }
        return arr;
    }
    public CustomerArray bubbleSort(CustomerArray arr) throws ArrayException {
        ArrayChanging a = new ArrayChanging();
        int len = arr.length();
        for(int i = 0; i < len; i++) {
            for(int j = 1; j < len; j++) {
                if(arr.getElement(j) < arr.getElement(j - 1)) {
                    a.swapTwoElements(arr, j, j - 1);
                }
            }
        }
        return arr;
    }
    public CustomerArray insertionSort(CustomerArray arr) throws ArrayException {
        int len = arr.length();
        for(int i = 1; i < len; i++) {
            int current = arr.getElement(i);
            int j = i;
            while(j > 0 && current < arr.getElement(j - 1)) {
                int elem = arr.getElement(j - 1);
                arr.setElement(j, elem);
                j--;
            }
            arr.setElement(j, current);
        }
        return arr;
    }
}
