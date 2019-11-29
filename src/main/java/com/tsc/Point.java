package com.tsc;

public class Point extends Shape {
    public Point(String id, int x, int y) {
        super(id,x,y);
    }

    @Override
    public String toString() {
        return String.format("P(%s,%d,%d)",super.ID,super.X,super.Y);
    }
}
