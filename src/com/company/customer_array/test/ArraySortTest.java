package com.company.customer_array.test;

import com.company.customer_array.action.ArraySort;
import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySortTest {
    ArraySort arraySort;
    CustomerArray customerArray;
    @BeforeClass
    public void setUp() {
        arraySort = new ArraySort();
    }
    @Test(dataProvider = "bubble")
    public void testBubbleSort(int[] array, int[] expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        customerArray = arraySort.bubbleSort(customerArray);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "insertion")
    public void testInsertionSort(int[] array, int[] expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        customerArray = arraySort.insertionSort(customerArray);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "insertion")
    public void testSelectionSort(int[] array, int[] expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        customerArray = arraySort.selectionSort(customerArray);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @DataProvider(name = "bubble")
    public Object[] createDataBubbleSort() {
        return new Object[][] { { new int[] {11, 2, -13, 1}, new int[] {-13, 1, 2, 11} },
                                { new int[] {-1, -2, -3, 4, 5, -6}, new int[] {-6, -3, -2, -1, 4, 5}},
                                { new int[] {-1, 0, 2}, new int[] {-1, 0, 2}}};
    }
    @DataProvider(name = "insertion")
    public Object[] createDataInsertionSort() {
        return new Object[][] { { new int[] {11, 2, -13, 1}, new int[] {-13, 1, 2, 11} },
                                { new int[] {-1, -2, -3, 4, 5, -6}, new int[] {-6, -3, -2, -1, 4, 5}},
                                { new int[] {-1, 0, 2}, new int[] {-1, 0, 2}}};
    }
    @DataProvider(name = "selection")
    public Object[] createDataSelectionSort() {
        return new Object[][] { { new int[] {11, 2, -13, 1}, new int[] {-13, 1, 2, 11} },
                                { new int[] {-1, -2, -3, 4, 5, -6}, new int[] {-6, -3, -2, -1, 4, 5}},
                                { new int[] {-1, 0, 2}, new int[] {-1, 0, 2}}};
    }
    @AfterClass
    public void tierDown() {
        arraySort = null;
        customerArray = null;
    }
}