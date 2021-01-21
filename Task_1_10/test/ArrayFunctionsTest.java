package test;

import main.Massif;
import main.ArrayFunctions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayFunctionsTest {
    ArrayFunctions f;
    Massif a0;
    Massif a1;
    Massif a2;
    @BeforeClass
    public void setUp() {
        f = new ArrayFunctions();
        a0 = new Massif(new int[]{1, 2, 3, -1});
        a1 = new Massif(new int[]{-1, -2, -3});
        a2 = new Massif(3);
        for (int i = 0; i < a2.getLength(); i++) {
            a2.setElement(i, i);
        }
    }
    @Test
    public void testMinElement() throws Exception {
        assertEquals(-1, f.getMinElement(a0));
        assertEquals(-3, f.getMinElement(a1));
        assertEquals(0, f.getMinElement(a2));
    }
    @Test
    public void testMaxElement() throws Exception {
        assertEquals(3, f.getMaxElement(a0));
        assertEquals(-1, f.getMaxElement(a1));
        assertEquals(2, f.getMaxElement(a2));
    }
    @Test
    public void testSumOfElements() throws Exception {
        assertEquals(5, f.getSumOfElements(a0));
        assertEquals(-6, f.getSumOfElements(a1));
        assertEquals(3, f.getSumOfElements(a2));
    }
    @Test
    public void testAvgOfElements() throws Exception {
        assertEquals(1.25, f.getAvgOfElements(a0),0.01);
        assertEquals(-2, f.getAvgOfElements(a1), 0.01);
        assertEquals(1, f.getAvgOfElements(a2),0.01);
    }
    @Test
    public void testCountOfPositiveElements() throws Exception {
        assertEquals(3, f.getCountOfPositiveElements(a0));
        assertEquals(0, f.getCountOfPositiveElements(a1));
        assertEquals(2, f.getCountOfPositiveElements(a2));
    }
    @Test
    public void testCountOfNegativeElements() throws Exception {
        assertEquals(1, f.getCountOfNegativeElements(a0));
        assertEquals(3, f.getCountOfNegativeElements(a1));
        assertEquals(0, f.getCountOfNegativeElements(a2));
    }
    @AfterClass
    public void tierDown() {
        f = null;
        a0 = null;
        a1 = null;
        a2 = null;
    }
}
