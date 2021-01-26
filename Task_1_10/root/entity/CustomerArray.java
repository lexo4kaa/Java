package root.entity;

import root.exception.ArrayException;

public class CustomerArray {
    private int[] arr;
    public CustomerArray(int[] arr) {
        this.arr = arr;
    }
    public CustomerArray(int n) throws ArrayException {
        if(n < 0) {
            throw new ArrayException("Negative array size: " + n);
        }
        arr = new int[n];
    }
    public int length() {
        return arr.length;
    }
    public int getElement(int i) throws ArrayException {
        if (checkRange(i)) {
            return arr[i];
        } else {
            throw new ArrayException("Index is out of range");
        }
    }
    public void setElement(int i, int value) throws ArrayException {
        if (checkRange(i)) {
            arr[i] = value;
        } else {
            throw new ArrayException("Index is out of range");
        }
    }
    private boolean checkRange(int i) {// check array range
        return (i >= 0 && i < arr.length);
    }
    @Override
    public String toString() {
        final String BLANK = " ";
        StringBuilder s = new StringBuilder("\nMatrix : " + arr.length + "\n");
        for (int value : arr) {
            s.append(value).append(BLANK);
        }
        return s.toString();
    }
}
