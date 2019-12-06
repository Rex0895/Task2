package com.tsc;

import java.util.LinkedList;
import java.util.List;

public abstract class Polygon extends Shape {
    private List<Shape> childShapes;

    public Polygon(String id, int x, int y) {
        super(id, x, y);
        childShapes = null;
    }

    public List<Shape> getChildShapesList() {
        return childShapes;
    }

    public void setChildShapesList(List<Shape> childShapes) {
        this.childShapes = new LinkedList<>(childShapes);
    }
}
