package com.tsc;

public class Point extends Shape {
    public Point(String id, int x, int y) {
        super(id,x,y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("P(%s,%d,%d)",super.getID(),super.getX(),super.getY()));
        return sb.toString();
    }
}
