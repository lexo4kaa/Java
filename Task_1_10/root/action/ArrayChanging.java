package root.action;

import root.entity.CustomerArray;

public class ArrayChanging {
    public CustomerArray swapTwoElements(CustomerArray arr, int first, int second) {
        int elem1 = arr.getElement(first);
        int elem2 = arr.getElement(second);
        arr.setElement(first, elem2);
        arr.setElement(second, elem1);
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
