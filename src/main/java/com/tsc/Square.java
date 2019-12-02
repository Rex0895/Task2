package com.tsc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Square extends Shape {
    private int length;
    private LinkedList<Shape> childShapes;

    public Square(String id, int x, int y, int len) {
        super(id, x, y);
        length = len;
        childShapes = null;
    }

    public Square(String id, int x, int y, int len, ArrayList<Shape> chShapes) {
        super(id, x, y);
        length = len;
        childShapes = new LinkedList<Shape>(chShapes);
    }

    public void setChildShapes(List<Shape> list) {
        childShapes = new LinkedList<>(list);
    }

    public void addToList(Shape shape) {
        childShapes.add(shape);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (childShapes == null)
            sb.append(String.format("S(%s,%d,%d,%d)", super.ID, super.X, super.Y, length));
        else {
            sb.append(String.format("S(%s,%d,%d,%d):\n", super.ID, super.X, super.Y, length));
            for (int i = 0; i < childShapes.size(); i++)
                if (i != childShapes.size() - 1) sb.append("\t-" + childShapes.get(i).toString() + "\n");
                else sb.append("\t-" + childShapes.get(i).toString());

        }
        return sb.toString();
    }
}
