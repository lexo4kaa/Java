package com.company.shape.parser;

import com.company.shape.entity.Point;
import com.company.shape.entity.Triangle;
import com.company.shape.exception.ShapeException;

import java.util.ArrayList;
import java.util.List;

import static com.company.shape.reader.TriangleReader.logger;

public class TriangleParser {
    private final static int NUMBER_OF_POINTS = 6;
    private final static String SEPARATORS = "[;@ ]";
    public static List<Triangle> dataParser(List<String> arrayList) throws ShapeException {
        if (arrayList == null) {
            logger.error("List is null");
            throw new ShapeException("List is null");
        }
        List<double[]> triangles = new ArrayList<>();
        for (String line : arrayList) {
            String[] splitLine = line.split(SEPARATORS);
            if(splitLine.length != NUMBER_OF_POINTS) {
                continue;
            }
            double[] resultArray = new double[splitLine.length];
            boolean flag = true;
            for (int i = 0; i < splitLine.length; i++) {
                String current = splitLine[i];
                try {
                    resultArray[i] = Double.parseDouble(current);
                } catch (NumberFormatException e) {
                    logger.error(current + " at position " + i + " is not an integer number");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                triangles.add(resultArray);
            }
        }
        return triangleParser(triangles);
    }

    public static List<Triangle> triangleParser(List<double[]> arrayList) throws ShapeException {
        if (arrayList == null) {
            logger.error("List is null");
            throw new ShapeException("List is null");
        }
        List<Triangle> triangleList = new ArrayList<>();
        for(int i = 0; i < arrayList.size(); i++) {
            double[] list = arrayList.get(i);
            List<Point> pointList = new ArrayList<>();
            for (int j = 0; j < NUMBER_OF_POINTS; j += 2) {
                Point point = new Point(list[j], list[j + 1]);
                pointList.add(point);
            }
            triangleList.add(new Triangle(pointList.get(0), pointList.get(1), pointList.get(2)));
        }
        return triangleList;
    }
}
