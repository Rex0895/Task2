package com.tsc.newstructure;

public abstract class Shape {
    private String id;
    private int x;
    private int y;

    private int globalX;
    private int globalY;

    public Shape(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        globalX = 0;
        globalY = 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGlobalX(int globalX) {
        this.globalX = globalX;
    }

    public void setGlobalY(int globalY) {
        this.globalY = globalY;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGlobalX() {
        return globalX;
    }

    public int getGlobalY() {
        return globalY;
    }

    public int[] getSecondPoint(){
        return new int[2];
    }
}
