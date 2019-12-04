package com.tsc.newstructure;

public class Rectangle extends Polygon{
    private int weight;
    private int height;
    public Rectangle(String id,int x,int y,int weight,int height){
        super(id,x,y);
        this.weight=weight;
        this.height=height;
    }

    @Override
    public int[] getSecondPoint() {
        return new int[]{getGlobalX()+weight,getGlobalY()+height};
    }
}
