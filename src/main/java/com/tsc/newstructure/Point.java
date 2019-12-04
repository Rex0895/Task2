package com.tsc.newstructure;

public class Point extends Shape {
    public Point(String id,int x,int y){
        super(id,x,y);
    }

    @Override
    public int[] getSecondPoint() {
        return new int[]{0,0};
    }
}
