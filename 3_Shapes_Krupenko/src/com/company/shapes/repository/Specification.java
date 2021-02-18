package com.company.shapes.repository;

import com.company.shapes.entity.Shape;

public interface Specification {
    boolean specify(Shape shape);
}
