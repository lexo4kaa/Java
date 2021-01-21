package main;

public class Massif {
    private int[] a;
    public Massif(int[] a) {
        this.a = a;
    }
    public Massif(int n) {
        if(n < 0) {
            throw new NegativeArraySizeException();
        }
        a = new int[n];
    }
    public int getLength() {
        return a.length;
    }
    public int getElement(int i) {
        if (checkRange(i)) { // check i
            return a[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    public void setElement(int i, int value) {
        if (checkRange(i)) { // check i
            a[i] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    private boolean checkRange(int i) {// check array range
        return (i >= 0 && i < a.length);
    }
    @Override
    public String toString() {
        final String BLANK = " ";
        StringBuilder s = new StringBuilder("\nMatrix : " + a.length + "\n");
        for (int value : a) {
            s.append(value).append(BLANK);
        }
        return s.toString();
    }
}
