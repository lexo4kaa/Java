package com.company.custommer_array.action;

import com.company.customer_array.action.ArraySort;
import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySortTest {
    ArraySort obj;
    CustomerArray customerArray;
    @BeforeClass
    public void setUp() {
        obj = new ArraySort();
    }
    @Test(dataProvider = "sort")
    public void testBubbleSort(int[] array, int[] expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        customerArray = obj.bubbleSort(customerArray);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "sort")
    public void testInsertionSort(int[] array, int[] expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        customerArray = obj.insertionSort(customerArray);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "sort")
    public void testSelectionSort(int[] array, int[] expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        customerArray = obj.selectionSort(customerArray);
        int[] actual = customerArray.getArray();
        assertEquals(actual, expected);
    }
    @DataProvider(name = "sort")
    public Object[] createDataSort() {
        return new Object[][] { { new int[] {11, 2, -13, 1}, new int[] {-13, 1, 2, 11} },
                { new int[] {-11, 2, 13, 1}, new int[] {-11, 1, 2, 13} },
                { new int[] {-1, -2, -3, 4, 5, -6}, new int[] {-6, -3, -2, -1, 4, 5}},
                { new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 2, 3, 4, 5, 6} },
                { new int[] {-1, 2, 0, 1}, new int[] {-1, 0, 1, 2}}};
    }
    @AfterClass
    public void tierDown() {
        obj = null;
        customerArray = null;
    }
}