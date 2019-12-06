package com.tsc;

public abstract class Shape {
    private String id;
    private int x;
    private int y;
    private Shape parrent;

    public Shape(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
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

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Shape getParrent() {
        return parrent;
    }

    public void setParrent(Shape parrent) {
        this.parrent = parrent;
    }

    public int getGlobalX() {
        if (parrent.equals(this)) return this.x;
        else return parrent.getX() + this.x;
    }

    public int getGlobalY() {
        if (parrent.equals(this)) return this.y;
        else return parrent.getY() + this.y;
    }

    public abstract int getSecondX();

    public abstract int getSecondY();
}
