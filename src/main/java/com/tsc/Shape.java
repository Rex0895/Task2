package com.tsc;

public class Shape {
    private String ID;
    private int X;
    private int Y;

    public Shape(String id,int x, int y){
        ID=id;
        X=y;
        Y=y;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
