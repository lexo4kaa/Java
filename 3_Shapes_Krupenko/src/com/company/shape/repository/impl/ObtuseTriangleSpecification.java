package com.company.shape.repository.impl;

import com.company.shape.action.TriangleProperty;
import com.company.shape.entity.Shape;
import com.company.shape.entity.Triangle;
import com.company.shape.repository.Specification;

public class ObtuseTriangleSpecification implements Specification {
    @Override
    public boolean specify(Shape shape) {
        if (shape == null || shape.getClass() != Triangle.class) {
            return false;
        }
        Triangle triangle = (Triangle) shape;
        return TriangleProperty.isObtuse(triangle);
    }
}