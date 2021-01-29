package com.company.customer_array.action;

import com.company.customer_array.entity.CustomerArray;

import java.util.stream.IntStream;

public class ArrayCalculationWithIntStream {
    public int minElement(CustomerArray array) {
        return IntStream.of(array.getArray())
                .min()
                .getAsInt();
    }
    public int maxElement(CustomerArray array) {
        return IntStream.of(array.getArray())
                .max()
                .getAsInt();
    }
    public int sumOfElements(CustomerArray array) {
        return IntStream.of(array.getArray())
                .sum();
    }
    public double avgOfElements(CustomerArray array) {
        return IntStream.of(array.getArray())
                .average()
                .getAsDouble();
    }
    public long countOfPositiveElements(CustomerArray array) {
        return IntStream.of(array.getArray())
                .filter(number -> number > 0)
                .count();
    }
    public long countOfNegativeElements(CustomerArray array) {
        return IntStream.of(array.getArray())
                .filter(number -> number < 0)
                .count();
    }
}
