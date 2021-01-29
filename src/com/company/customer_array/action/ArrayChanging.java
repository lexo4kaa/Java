package com.company.customer_array.action;

import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;

public class ArrayChanging {
    public CustomerArray swapTwoElements(CustomerArray array, int position1, int position2) throws ArrayException {
        int element1 = array.getElement(position1);
        int element2 = array.getElement(position2);
        array.setElement(position1, element2);
        array.setElement(position2, element1);
        return array;
    }
    public CustomerArray mod(CustomerArray array, int value) throws ArrayException {
        int length = array.length();
        for(int i = 0; i < length; i++) {
            int current = array.getElement(i);
            array.setElement(i, current % value);
        }
        return array;
    }
}
