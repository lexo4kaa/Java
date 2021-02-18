package com.company.shapes.repository;

import com.company.shapes.entity.Shape;
import com.company.shapes.exception.ShapeException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapeRepository {
    private static ShapeRepository repository = new ShapeRepository();
    private List<Shape> shapes = new ArrayList<>();

    private ShapeRepository() {
    }

    public static ShapeRepository getShapeRepository () {
        if (repository == null) {
            repository = new ShapeRepository();
        }
        return repository;
    }

    public boolean addShape(Shape shape) throws ShapeException {
        if (shape == null) {
            throw new ShapeException("shape repository cannot store null");
        }
        return shapes.add(shape);
    }

    public boolean removeShape(Shape shape) {
        return this.shapes.remove(shape);
    }

    public Shape setShape(int index, Shape shape) throws ShapeException {
        if (shape == null) {
            throw new ShapeException("shape repository cannot store null");
        }
        return shapes.set(index, shape);
    }

    public Shape getShape(int index) {
        return shapes.get(index);
    }

    public List<Shape> query(Specification specification) {
        List<Shape> requestedShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            if (specification.specify(shape)) {
                requestedShapes.add(shape);
            }
        }
        return requestedShapes;
    }
    public List<Shape> sort(Comparator<Shape> comparator) {
        shapes.sort(comparator);
        return shapes;
    }
}