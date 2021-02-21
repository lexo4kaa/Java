package com.company.shape.action;

import com.company.shape.entity.Point;
import com.company.shape.entity.Triangle;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TriangleCalculationTest {
    TriangleCalculation obj;
    @BeforeClass
    public void setUp() {
        obj = new TriangleCalculation();
    }
    @Test(dataProvider = "perimeter")
    public void testTrianglePerimeter(Triangle triangle, double expected ) {
        double actual = obj.perimeter(triangle);
        assertEquals(actual, expected, Math.pow(10, -10));
    }
    @Test(dataProvider = "square")
    public void testTriangleSquare(Triangle triangle, double expected ) {
        double actual = obj.square(triangle);
        assertEquals(actual, expected, Math.pow(10, -10));
    }
    @DataProvider(name = "perimeter")
    public Object[] createDataPerimeter() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , 2 + Math.sqrt(2) },
                { new Triangle(new Point(2,0), new Point(0, 2), new Point(0,-2)) , 4 + 2*Math.sqrt(8) },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , 12 } };
    }
    @DataProvider(name = "square")
    public Object[] createDataSquare() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , 0.5 },
                { new Triangle(new Point(2,0), new Point(0, 2), new Point(0,-2)) , 4 },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , 6 } };
    }
    @AfterClass
    public void tierDown() {
        obj = null;
    }
}