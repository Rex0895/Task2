package com.tsc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rectangle extends Shape {
    private int weight;
    private int height;
    private LinkedList<Shape> childShapes;

    public Rectangle(String id, int x, int y, int weight, int height) {
        super(id, x, y);
        this.weight = weight;
        this.height = height;
    }

    public Rectangle(String id, int x, int y, int weight, int height, ArrayList<Shape> chShapes) {
        super(id, x, y);
        this.weight = weight;
        this.height = height;
        childShapes = new LinkedList<Shape>(chShapes);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setChildShapes(List<Shape> list) {
        childShapes = new LinkedList<>(list);
    }

    public LinkedList<Shape> getChildShapes() {
        return childShapes;
    }

    public void addToList(Shape shape) {
        childShapes.add(shape);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (childShapes == null)
            sb.append(String.format("R(%s,%d,%d,%d,%d)", super.ID, super.X, super.Y, weight, height));
        else {
            sb.append(String.format("R(%s,%d,%d,%d,%d):\n", super.ID, super.X, super.Y, weight, height));
            for (int i = 0; i < childShapes.size(); i++)
                if (i != childShapes.size() - 1) sb.append("\t-" + childShapes.get(i).toString() + "\n");
                else sb.append("\t-" + childShapes.get(i).toString());
        }
        return sb.toString();
    }
}
