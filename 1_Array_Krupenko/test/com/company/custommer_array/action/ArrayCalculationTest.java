package com.company.custommer_array.action;

import com.company.customer_array.action.ArrayCalculation;
import com.company.customer_array.entity.CustomerArray;
import com.company.customer_array.exception.ArrayException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayCalculationTest {
    ArrayCalculation obj;
    CustomerArray customerArray;
    @BeforeClass
    public void setUp() {
        obj = new ArrayCalculation();
    }
    @Test(dataProvider = "min")
    public void testMinElement(int[] array, int expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        int actual = obj.minElement(customerArray);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "max")
    public void testMaxElement(int[] array, int expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        int actual = obj.maxElement(customerArray);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "sum")
    public void testSumOfElement(int[] array, int expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        int actual = obj.sumOfElements(customerArray);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "avg")
    public void testAvgOfElement(int[] array, double expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        double actual = obj.avgOfElements(customerArray);
        assertEquals(actual, expected, 0.001);
    }
    @Test(dataProvider = "positive_count")
    public void testCountOfPositiveElements(int[] array, int expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        int actual = obj.countOfPositiveElements(customerArray);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "negative_count")
    public void testCountOfNegativeElements(int[] array, int expected ) throws ArrayException {
        customerArray = new CustomerArray(array);
        int actual = obj.countOfNegativeElements(customerArray);
        assertEquals(actual, expected);
    }
    @DataProvider(name = "min")
    public Object[] createDataMin() {
        return new Object[][] { { new int[] {11, 2, -13, 17, 3, -1}, -13 },
                                { new int[]{-1, -2, -3, 4, 5, -6}, -6},
                                { new int[]{-1, 0, 2}, -1}};
    }
    @DataProvider(name = "max")
    public Object[] createDataMax() {
        return new Object[][] { { new int[] {11, 2, -13, 17, 3, -1}, 17 },
                                { new int[]{-1, -2, -3, 4, 5, -6}, 5},
                                { new int[]{-1, 0, 2}, 2}};
    }
    @DataProvider(name = "sum")
    public Object[] createDataSum() {
        return new Object[][] { { new int[] {11, 2, -13, 17, 3, -1}, 19 },
                                { new int[]{-1, -2, -3, 4, 5, -6}, -3},
                                { new int[]{-1, 0, 2}, 1}};
    }
    @DataProvider(name = "avg")
    public Object[] createDataAvg() {
        return new Object[][] { { new int[] {11, 2, -13, 17, 3, -1},  19./6 },
                                { new int[]{-1, -2, -3, 4, 5, -6}, -0.5},
                                { new int[]{-1, 0, 2}, 1./3}};
    }
    @DataProvider(name = "positive_count")
    public Object[] createDataPositiveCount() {
        return new Object[][] { { new int[] {11, 2, -13, 17, 3, -1},  4 },
                                { new int[]{-1, -2, -3, 4, 5, -6}, 2 },
                                { new int[]{-1, 0, 2}, 1}};
    }
    @DataProvider(name = "negative_count")
    public Object[] createDataNegativeCount() {
        return new Object[][] { { new int[] {11, 2, -13, 17, 3, -1},  2 },
                                { new int[]{-1, -2, -3, 4, 5, -6}, 4 },
                                { new int[]{-1, 0, 2}, 1}};
    }
    @AfterClass
    public void tierDown() {
        obj = null;
        customerArray = null;
    }
}
