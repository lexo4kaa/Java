package root.action;

import root.entity.CustomerArray;

public class ArrayChanging {
    public CustomerArray swapTwoElements(CustomerArray arr, int elem1, int elem2) {
        int first = arr.getElement(elem1);
        int second = arr.getElement(elem2);
        arr.setElement(elem1, second);
        arr.setElement(elem2, first);
        return arr;
    }
    public CustomerArray mod(CustomerArray arr, int value)  {
        int len = arr.length();
        for(int i = 0; i < len; i++) {
            int current = arr.getElement(i);
            arr.setElement(i, current % value);
        }
        return arr;
    }
}
