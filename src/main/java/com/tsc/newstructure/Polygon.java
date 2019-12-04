package com.tsc.newstructure;

import java.util.LinkedList;
import java.util.List;

public abstract class Polygon extends Shape {
    private List<Shape> childShapes;

    public Polygon(String id, int x, int y) {
        super(id, x, y);
        childShapes = null;
    }

    public void addToChildList(Shape s) {
        if (childShapes == null) {
            childShapes = new LinkedList<>();
        }
        s.setGlobalX(s.getX() + getGlobalX());
        s.setGlobalY(s.getY() + getGlobalY());
        childShapes.add(s);
    }

    public List<Shape> getChildShapesList() {
        if (childShapes != null) return childShapes;
        else {
            System.out.println("Список фигур-наследников фигуры " + getId() + " пуст");
            return null;
        }
    }
    //public void setChildShapes()
}
