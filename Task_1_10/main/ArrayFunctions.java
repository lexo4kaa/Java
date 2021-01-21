package main;

public class ArrayFunctions {
    public int getMinElement(Massif a) {
        int min = a.getElement(0);
        for (int i = 1; i < a.getLength(); i++) {
            if(a.getElement(i) < min) {
                min = a.getElement(i);
            }
        }
        return min;
    }
    public int getMaxElement(Massif a) {
        int max = a.getElement(0);
        for (int i = 1; i < a.getLength(); i++) {
            if(a.getElement(i) > max) {
                max = a.getElement(i);
            }
        }
        return max;
    }
    public int getSumOfElements(Massif a) {
        int sum = 0;
        for (int i = 0; i < a.getLength(); i++) {
            sum += a.getElement(i);
        }
        return sum;
    }
    public double getAvgOfElements(Massif a) {
        return (double)getSumOfElements(a)/(double)a.getLength();
    }
    public int getCountOfPositiveElements(Massif a) {
        int count = 0;
        for (int i = 0; i < a.getLength(); i++) {
            if(a.getElement(i) > 0) {
                count++;
            }
        }
        return count;
    }
    public int getCountOfNegativeElements(Massif a) {
        int count = 0;
        for (int i = 0; i < a.getLength(); i++) {
            if(a.getElement(i) < 0) {
                count++;
            }
        }
        return count;
    }

}
