package com.company.shape.comparator;

import com.company.shape.entity.Point;
import com.company.shape.entity.Shape;
import com.company.shape.entity.Triangle;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TriangleComparatorTest {
    Shape shape1;
    Shape shape2;
    Shape shape3;
    @BeforeClass
    public void setUp() {
        shape1 = new Triangle(new Point(0,0), new Point(0,5), new Point(5, 0));
        shape1.setShapeId("ABC");
        shape2 = new Triangle(new Point(0, 0), new Point(4,3), new Point(0,3));
        shape2.setShapeId("ABD");
        shape3 = new Triangle(new Point(-10,10), new Point(0,10), new Point(0, 20));
        shape3.setShapeId("ABC");
    }
    @Test(dataProvider = "x")
    public void testXCompare(Shape shapeA, Shape shapeB, int expected ) {
        int actual = TriangleComparator.X.compare(shapeA, shapeB);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "y")
    public void testYCompare(Shape shapeA, Shape shapeB, int expected ) {
        int actual = TriangleComparator.Y.compare(shapeA, shapeB);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "perimeter")
    public void testPerimeterCompare(Shape shapeA, Shape shapeB, int expected ) {
        int actual = TriangleComparator.PERIMETER.compare(shapeA, shapeB);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "square")
    public void testSquareCompare(Shape shapeA, Shape shapeB, int expected ) {
        int actual = TriangleComparator.SQUARE.compare(shapeA, shapeB);
        assertEquals(actual, expected);
    }
    @Test(dataProvider = "id")
    public void testIdCompare(Shape shapeA, Shape shapeB, int expected ) {
        int actual = TriangleComparator.ID.compare(shapeA, shapeB);
        assertEquals(actual, expected);
    }
    @DataProvider(name = "x")
    public Object[] createDataX() {
        return new Object[][] { { shape1, shape2 , 0 },
                { shape1, shape3, 1},
                { shape2, shape3, 1} };
    }
    @DataProvider(name = "y")
    public Object[] createDataY() {
        return new Object[][] { { shape1, shape2 , 0 },
                { shape1, shape3, -1},
                { shape2, shape3, -1} };
    }
    @DataProvider(name = "perimeter")
    public Object[] createDataPerimeter() {
        return new Object[][] { { shape1, shape2 , 1 },
                { shape1, shape3, -1},
                { shape2, shape3, -1} };
    }
    @DataProvider(name = "square")
    public Object[] createDataSquare() {
        return new Object[][] { { shape1, shape2 , 1 },
                { shape1, shape3, -1},
                { shape2, shape3, -1} };
    }
    @DataProvider(name = "id")
    public Object[] createDataId() {
        return new Object[][] { { shape1, shape2 , -1 },
                { shape1, shape3, 0},
                { shape2, shape3, 1 } };
    }
    @AfterClass
    public void tierDown() {
        shape1 = null;
        shape2 = null;
        shape3 = null;
    }
}