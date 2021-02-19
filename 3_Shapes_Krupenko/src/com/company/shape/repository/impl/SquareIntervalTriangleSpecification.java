package com.company.shape.repository.impl;

import com.company.shape.action.TriangleCalculation;
import com.company.shape.entity.Shape;
import com.company.shape.entity.Triangle;
import com.company.shape.repository.Specification;

public class SquareIntervalTriangleSpecification implements Specification {
    private TriangleCalculation calculation;
    private double from;
    private double to;

    public SquareIntervalTriangleSpecification(double from, double to) {
        this.from = from;
        this.to = to;
        calculation = new TriangleCalculation();
    }

    @Override
    public boolean specify(Shape shape) {
        double perimeter = calculation.square((Triangle)shape);
        return perimeter >= from && perimeter <= to;
    }
}
