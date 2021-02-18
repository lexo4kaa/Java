package com.company.shapes.repository.impl;

import com.company.shapes.action.TriangleProperty;
import com.company.shapes.entity.Shape;
import com.company.shapes.entity.Triangle;
import com.company.shapes.repository.Specification;

public class RectangularTriangleSpecification implements Specification {
    @Override
    public boolean specify(Shape shape) {
        if (shape == null || shape.getClass() != Triangle.class) {
            return false;
        }
        Triangle triangle = (Triangle) shape;
        boolean result = TriangleProperty.isRectangular(triangle);
        return result;
    }
}
