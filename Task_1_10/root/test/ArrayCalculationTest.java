package root.test;

import root.entity.CustomerArray;
import root.action.ArrayCalculation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import root.exception.ArrayException;

import static org.testng.Assert.assertEquals;

public class ArrayCalculationTest {
    ArrayCalculation f;
    CustomerArray a0;
    CustomerArray a1;
    @BeforeClass
    public void setUp() {
        f = new ArrayCalculation();
        a0 = new CustomerArray(new int[]{1, 2, 3, -1});
        a1 = new CustomerArray(new int[]{-1, -2, -3});
    }
    @Test
    public void testMinElement() throws ArrayException {
        assertEquals(-1, f.minElement(a0));
        assertEquals(-3, f.minElement(a1));
    }
    @Test
    public void testMaxElement() throws ArrayException {
        assertEquals(3, f.maxElement(a0));
        assertEquals(-1, f.maxElement(a1));
    }
    @Test
    public void testSumOfElements() throws ArrayException {
        assertEquals(5, f.sumOfElements(a0));
        assertEquals(-6, f.sumOfElements(a1));
    }
    @Test
    public void testAvgOfElements() throws ArrayException {
        assertEquals(1.25, f.avgOfElements(a0),0.01);
        assertEquals(-2, f.avgOfElements(a1), 0.01);
    }
    @Test
    public void testCountOfPositiveElements() throws ArrayException {
        assertEquals(3, f.countOfPositiveElements(a0));
        assertEquals(0, f.countOfPositiveElements(a1));
    }
    @Test
    public void testCountOfNegativeElements() throws ArrayException {
        assertEquals(1, f.countOfNegativeElements(a0));
        assertEquals(3, f.countOfNegativeElements(a1));
    }
    @AfterClass
    public void tierDown() {
        f = null;
        a0 = null;
        a1 = null;
    }
}
