package com.company.shapes.entity;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Shape {
    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(Point point1, Point point2, Point point3) {
        new Shape();
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    public List<Point> getAllPoints() {
        List<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);
        return points;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((point1 == null) ? 0 : point1.hashCode());
        result = prime * result + ((point2 == null) ? 0 : point2.hashCode());
        result = prime * result + ((point3 == null) ? 0 : point3.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triangle other = (Triangle) obj;
        if (point1 == null) {
            if (other.point1 != null) {
                return false;
            }
        } else if (!point1.equals(other.point1)) {
            return false;
        }
        if (point2 == null) {
            if (other.point2 != null) {
                return false;
            }
        } else if (!point2.equals(other.point2)) {
            return false;
        }
        if (point3 == null) {
            if (other.point3 != null) {
                return false;
            }
        } else if (!point3.equals(other.point3)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Triangle\n");
        sb.append(point1).append("\n");
        sb.append(point2).append("\n");
        sb.append(point3).append("\n");
        return sb.toString();
    }
}
