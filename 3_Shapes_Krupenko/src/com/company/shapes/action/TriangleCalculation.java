package com.company.shapes.action;

import com.company.shapes.entity.Point;
import com.company.shapes.entity.Triangle;

public class TriangleCalculation {
    public double distance(Point point1, Point point2) {
        double distanceX = point1.getX() - point2.getX();
        double distanceY = point1.getY() - point2.getY();
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }

    public double perimeter(Triangle triangle) {
        Point point1 = triangle.getPoint1();
        Point point2 = triangle.getPoint2();
        Point point3 = triangle.getPoint3();
        return distance(point1, point2) + distance(point1, point3) + distance(point2, point3);
    }

    public double square(Triangle triangle) {
        Point point1 = triangle.getPoint1();
        Point point2 = triangle.getPoint2();
        Point point3 = triangle.getPoint3();
        double a = distance(point1, point2);
        double b = distance(point1, point3);
        double c = distance(point2, point3);
        double p = perimeter(triangle) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
