package com.tsc;

class Rectangle extends Polygon {
    private int weight;
    private int height;

    Rectangle(String id, int x, int y, int weight, int height) {
        super(id, x, y);
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int getSecondX() {
        return getGlobalX() + weight;
    }

    public int getSecondY() {
        return getGlobalY() + height;
    }

    @Override
    public String toString() {
        return "R(" + getId() + "," + getX() + "," + getY() + "," + weight + "," + height + ")";
    }
}
