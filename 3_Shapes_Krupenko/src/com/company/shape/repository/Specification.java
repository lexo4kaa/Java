package com.company.shape.repository;

import com.company.shape.entity.Shape;

public interface Specification {
    boolean specify(Shape shape);
}
