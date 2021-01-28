package com.company.customer_array.action;

import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;

public class ArrayChanging {
    public CustomerArray swapTwoElements(CustomerArray array, int elem1, int elem2) throws ArrayException {
        int first = array.getElement(elem1);
        int second = array.getElement(elem2);
        array.setElement(elem1, second);
        array.setElement(elem2, first);
        return array;
    }
    public CustomerArray mod(CustomerArray array, int value) throws ArrayException {
        int len = array.length();
        for(int i = 0; i < len; i++) {
            int current = array.getElement(i);
            array.setElement(i, current % value);
        }
        return array;
    }
}
