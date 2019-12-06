package com.tsc;

class Square extends Polygon {
    private int length;

    Square(String id, int x, int y, int length) {
        super(id, x, y);
        this.length = length;
    }

    @Override
    public int getSecondX() {
        return getGlobalX() + length;
    }

    public int getSecondY() {
        return getGlobalY() + length;
    }

    @Override
    public String toString() {
        return "S(" + getId() + "," + getX() + "," + getY() + "," + length + ")";
    }

}
