package com.company.shape.observer;

import com.company.shape.exception.ShapeException;

public interface CustomerObserver {
    void parameterChanged (TriangleEvent event) throws ShapeException;
}
