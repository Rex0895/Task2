package com.tsc.newstructure;

public class Square extends Polygon{
    private int length;
    public Square(String id,int x,int y,int length){
        super(id,x,y);
        this.length=length;
    }
    @Override
    public int[] getSecondPoint() {
        return new int[]{getGlobalX()+length,getGlobalY()+length};
    }
}
