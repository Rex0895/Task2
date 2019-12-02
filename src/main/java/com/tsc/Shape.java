package com.tsc;

public class Shape {
    protected String ID;
    protected int X;
    protected int Y;

    public Shape(String id, int x, int y) {
        ID = id;
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
