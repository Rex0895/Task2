package com.tsc;

class Point extends Shape {
    Point(String id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public int getSecondX() {
        return 0;
    }

    public int getSecondY() {
        return 0;
    }

    @Override
    public String toString() {
        return "P(" + getId() + "," + getX() + "," + getY() + ")";
    }
}
