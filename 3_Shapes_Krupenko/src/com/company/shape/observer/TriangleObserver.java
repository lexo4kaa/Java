package com.company.shape.observer;

import com.company.shape.action.TriangleCalculation;
import com.company.shape.entity.Triangle;
import com.company.shape.exception.ShapeException;
import com.company.shape.warehouse.ShapeValue;
import com.company.shape.warehouse.Warehouse;

import static com.company.shape.reader.TriangleReader.logger;

public class TriangleObserver implements CustomerObserver {
    @Override
    public void parameterChanged(TriangleEvent event) throws ShapeException {
        if (event == null) {
            logger.error("argument is null");
            throw new ShapeException("argument is null");
        }
        Triangle triangle = event.getSource();
        TriangleCalculation action = new TriangleCalculation();
        double perimeter = action.perimeter(triangle);
        double square = action.square(triangle);
        ShapeValue shapeValue = new ShapeValue(perimeter, square);
        Warehouse warehouse = Warehouse.getWarehouse();
        warehouse.putShapeValue(triangle.getShapeId(), shapeValue);
    }
}
