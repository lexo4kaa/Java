package root.entity;

public class CustomerArray {
    private int[] arr;
    public CustomerArray(int[] arr) {
        this.arr = arr;
    }
    public CustomerArray(int n) {
        if(n < 1) {
            throw new NegativeArraySizeException();
        }
        arr = new int[n];
    }
    public int length() {
        return arr.length;
    }
    public int getElement(int i) {
        if (checkRange(i)) {
            return arr[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    public void setElement(int i, int value) {
        if (checkRange(i)) {
            arr[i] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
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
