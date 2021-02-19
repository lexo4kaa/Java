package com.company.shape.repository.impl;

import com.company.shape.action.TriangleCalculation;
import com.company.shape.entity.Shape;
import com.company.shape.entity.Triangle;
import com.company.shape.repository.Specification;

public class PerimeterIntervalTriangleSpecification implements Specification {
    private TriangleCalculation calculation;
    private double from;
    private double to;

    public PerimeterIntervalTriangleSpecification(double from, double to) {
        this.from = from;
        this.to = to;
        calculation = new TriangleCalculation();
    }

    @Override
    public boolean specify(Shape shape) {
        double perimeter = calculation.perimeter((Triangle)shape);
        return perimeter >= from && perimeter <= to;
    }
}
