package root.entity;

import root.exception.ArrayException;

public class CustomerArray {
    private int[] array;
    public CustomerArray(int[] array) {
        this.array = array;
    }
    public CustomerArray(int n) throws ArrayException {
        if(n < 0) {
            throw new ArrayException("Negative array size: " + n);
        }
        array = new int[n];
    }
    public int length() {
        return array.length;
    }
    public int getElement(int i) throws ArrayException {
        if (checkRange(i)) {
            return array[i];
        } else {
            throw new ArrayException("Index is out of range");
        }
    }
    public void setElement(int i, int value) throws ArrayException {
        if (checkRange(i)) {
            array[i] = value;
        } else {
            throw new ArrayException("Index is out of range");
        }
    }
    public int[] getArray() {
        return array;
    }
    private boolean checkRange(int i) {// check array range
        return (i >= 0 && i < array.length);
    }
    @Override
    public String toString() {
        final String BLANK = " ";
        StringBuilder s = new StringBuilder("\nMatrix : " + array.length + "\n");
        for (int value : array) {
            s.append(value).append(BLANK);
        }
        return s.toString();
    }
}
