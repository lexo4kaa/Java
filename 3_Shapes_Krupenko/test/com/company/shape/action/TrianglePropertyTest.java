package com.company.shape.action;

import com.company.shape.entity.Point;
import com.company.shape.entity.Triangle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TrianglePropertyTest {
    @Test(dataProvider = "rectangular")
    public void testRectangularTriangle(Triangle triangle, boolean expected ) {
        boolean actual = TriangleProperty.isRectangular(triangle);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "acute")
    public void testAcuteTriangle(Triangle triangle, boolean expected ) {
        boolean actual = TriangleProperty.isAcute(triangle);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "obtuse")
    public void testObtuseTriangle(Triangle triangle, boolean expected ) {
        boolean actual = TriangleProperty.isObtuse(triangle);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "isosceles")
    public void testIsoscelesTriangle(Triangle triangle, boolean expected ) {
        boolean actual = TriangleProperty.isIsosceles(triangle);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "equilateral")
    public void testEquilateralTriangle(Triangle triangle, boolean expected ) {
        boolean actual = TriangleProperty.isEquilateral(triangle);
        assertEquals(actual, expected);
    }
    @DataProvider(name = "rectangular")
    public Object[] createDataRectangularTriangle() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , true },
                { new Triangle(new Point(5,0), new Point(7, 1), new Point(0,0)), false },
                { new Triangle(new Point(5,0), new Point(3, 1), new Point(0,0)), false },
                { new Triangle(new Point(0,0), new Point(2*Math.sqrt(3), 0), new Point(Math.sqrt(3),3)) , false },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , true } };
    }
    @DataProvider(name = "acute")
    public Object[] createDataAcuteTriangle() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , false },
                { new Triangle(new Point(5,0), new Point(7, 1), new Point(0,0)), false },
                { new Triangle(new Point(5,0), new Point(3, 10), new Point(0,0)), true },
                { new Triangle(new Point(0,0), new Point(2*Math.sqrt(3), 0), new Point(Math.sqrt(3),3)) , true },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , false } };
    }
    @DataProvider(name = "obtuse")
    public Object[] createDataObtuseTriangle() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , false },
                { new Triangle(new Point(5,0), new Point(7, 1), new Point(0,0)), true },
                { new Triangle(new Point(5,0), new Point(3, 1), new Point(0,0)), true },
                { new Triangle(new Point(0,0), new Point(2*Math.sqrt(3), 0), new Point(Math.sqrt(3),3)) , false },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , false } };
    }
    @DataProvider(name = "isosceles")
    public Object[] createDataIsoscelesTriangle() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , true },
                { new Triangle(new Point(5,0), new Point(7, 1), new Point(0,0)), false },
                { new Triangle(new Point(0,0), new Point(1, 3), new Point(2,0)), true },
                { new Triangle(new Point(0,0), new Point(2*Math.sqrt(3), 0), new Point(Math.sqrt(3),3)) , false },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , false } };
    }
    @DataProvider(name = "equilateral")
    public Object[] createDataEquilateralTriangle() {
        return new Object[][] { { new Triangle(new Point(0,0), new Point(0, 1), new Point(1,0)) , false },
                { new Triangle(new Point(5,0), new Point(7, 1), new Point(0,0)), false },
                { new Triangle(new Point(-1,0), new Point(1, 0), new Point(0, Math.sqrt(3))), true },
                { new Triangle(new Point(0,0), new Point(2*Math.sqrt(3), 0), new Point(Math.sqrt(3),3)) , true },
                { new Triangle(new Point(0,0), new Point(0, 3), new Point(4,3)) , false } };
    }
}
