package com.tsc;

class Point extends Shape {
    Point(String id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public int getSecondX() {
        return getGlobalX()-1;
    }

    public int getSecondY() {
        return getGlobalY()-1;
    }

    @Override
    public String toString() {
        return "P(" + getId() + "," + getX() + "," + getY() + ")";
    }
}
